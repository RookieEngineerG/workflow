package com.hitotek.workflow.model.multipart;

import com.hitotek.workflow.factory.DataFactory;
import com.hitotek.workflow.model.Data;
import com.hitotek.workflow.model.multipart.callback.ConvertCallback;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description
 * @date 2019/1/9
 */
@Component
public class MultiPartDataManager {
    private MergeCallback mergeCallback;
    private KeyConverter keyConverter = new KeyConverter();
    private Map<String, Boolean> notUnique = new HashMap<>();

    public MultiPartDataManager process(MergeCallback callback) {
        this.mergeCallback = callback;
        return this;
    }

    /**
     * 转化数据 PO -> MultipartData
     *
     * @param targetList 目标PO集合
     * @param <T>        PO泛型
     * @return 转化后的数据
     */
    public <T> List<MultipartData> translateTo(List<T> targetList) {
        if (null == targetList) {
            return new ArrayList<>();
        }
        MultipartData multipartData;
        List<MultipartData> dataList = new ArrayList<>();
        for (T t : targetList) {
            Field[] fields = t.getClass().getDeclaredFields();
            multipartData = new MultipartData();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object o = field.get(t);
                    multipartData.include(field.getName(), o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                field.setAccessible(false);
            }
            dataList.add(multipartData);
        }
        return dataList;
    }

    public void notUnique(String... key) {
        for (String s : key) {
            this.notUnique.put(s, true);
        }
    }

    public List<MultipartData> convert(List<MultipartData> multipartData) {
        return convert(multipartData, null);
    }

    public List<MultipartData> convert(List<MultipartData> multipartData, ConvertCallback callback) {
        List<MultipartData> multipartDataList = new ArrayList<>();
        for (MultipartData multipartDatum : multipartData) {
            if (null != callback) {
                callback.process(multipartDatum);
            }
            multipartDataList.add(multipartDatum.translate2Camel());
        }
        return multipartDataList;
    }

    public <T> List<T> getList(String key, List<MultipartData> multipartData, Class<T> tClass) {
        List<T> tList = new ArrayList<>();
        for (MultipartData data : multipartData) {
            T part = data.getPart(key, tClass);
            if (part != null) {
                tList.add(part);
            }
        }
        keyConverter = null;
        return tList;
    }

    /**
     * 使用适配器进行转化
     *
     * @param adapter           目标适配器
     * @param multipartDataList 待转数据集
     * @return 适配器数据结构的数据集
     */
    public List<MultipartData> translateTo(MultipartDataAdapter adapter, List<MultipartData> multipartDataList) {
        List<MultipartData> data = new ArrayList<>();
        MultipartData temp;
        for (MultipartData multipartData : multipartDataList) {
            temp = new MultipartData();
            for (String key : multipartData.keySet()) {
                String targetKey = keyConverter.convert(key);
                if (adapter.contains(targetKey)) {
                    temp.include(targetKey, multipartData.get(key));
                }
            }
            data.add(temp);
        }
        return data;
    }

    /**
     * 转化数据 -> PO
     *
     * @param multipartData 数据集
     * @param target        目标PO
     * @param <T>           PO
     * @return 符合要求的集合
     */
    public <T> List<T> translateTo(List<MultipartData> multipartData, Class<T> target, String... ignore) {
        List<String> ignores = Arrays.asList(ignore);
        Field[] declaredFields = target.getDeclaredFields();
        Map<String, Field> fieldMap = new HashMap<>(declaredFields.length);
        List<T> result = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            fieldMap.put(declaredField.getName(), declaredField);
        }
        T sample = null;
        for (MultipartData data : multipartData) {
            // 转化key
            data = data.translate2Camel();
            try {
                sample = target.newInstance();
                for (String key : data.keySet()) {
                    if (!ignores.contains(key)) {
                        Field field = fieldMap.get(key);
                        if (field != null) {
                            field.setAccessible(true);
                            field.set(sample, data.get(key));
                            field.setAccessible(false);
                        }
                    }
                }
                if (!isLogicalEmpty(sample)) {
                    result.add(sample);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private boolean isLogicalEmpty(Object target) {
        Field[] declaredFields = target.getClass().getDeclaredFields();
        int count = 0;
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            try {
                if (declaredField.get(target) == null) {
                    count++;
                }
            } catch (IllegalAccessException e) {
                return false;
            }
            declaredField.setAccessible(false);
        }
        return count > declaredFields.length - 1;
    }

    private boolean isNotUnique(String key) {
        if (this.notUnique.size() == 0) {
            return false;
        }
        if (this.notUnique.get(key) == null) {
            return false;
        }
        return this.notUnique.get(key);
    }

    /**
     * 转化数据并合并
     *
     * @param target 目标数据集合
     * @param <T>    泛型
     * @return 数据
     */
    public <T> Data translateAndMerge(List<T> target) {
        return this.merge(this.translateTo(target));
    }

    public <T> Data translateAndMerge(List<T> target, boolean unique) {
        return this.merge(this.translateTo(target), unique);
    }

    public Data merge(List<MultipartData> dataList) {
        return this.merge(dataList, false);
    }

    @SuppressWarnings("unchecked")
    public MultipartData merge(List<MultipartData> dataList, boolean unique) {
        Data data = DataFactory.
                createDefault();
        Part part = null;
        MultipartData temp = null;
        MultipartData target = new MultipartData();
        for (MultipartData multipartData : dataList) {
            temp = new MultipartData();
            part = new Part();
            part.setParent(multipartData);
            for (String key : multipartData.keySet()) {
                String convertKey = keyConverter.convert(key);
                part.setKey(convertKey);
                Object value = multipartData.get(key);
                // 普通情况直接放入值
                if (target.get(convertKey) == null) {
                    target.put(convertKey, value);
                } else {
                    Object targetValue = target.get(convertKey);
                    // 值出现冲突，将原有值放入一个列表，进行合并
                    if (!(targetValue instanceof List)) {
                        if (value.equals(targetValue) && !isNotUnique(convertKey)) {
                            continue;
                        }
                        target.include(convertKey, new ArrayList<>());
                        if (!targetValue.equals(value) || !unique || isNotUnique(convertKey)) {
                            target.getListPart(convertKey, Object.class).add(targetValue);
                            if (mergeCallback != null) {
                                temp.include(convertKey, targetValue);
                                part.setPartValue(temp);
                                mergeCallback.onMergeAppear(part, target);
                            }
                        }
                        temp.include(convertKey, value);
                        part.setPartValue(temp);
                        mergeCallback.onMergeAppear(part, target);
                        target.getListPart(convertKey, Object.class).add(value);
                        continue;
                    }
                    if (unique) {
                        List<Object> listPart = target.getListPart(convertKey, Object.class);
                        if (!listPart.contains(value) || isNotUnique(convertKey)) {
                            listPart.add(value);
                        }
                    } else {
                        target.getListPart(convertKey, Object.class).add(value);
                    }
                }
            }
        }
        if (mergeCallback != null) {
            mergeCallback.completeMerge(target);
        }
        return target;
    }

}
