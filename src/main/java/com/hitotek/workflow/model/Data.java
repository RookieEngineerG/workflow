package com.hitotek.workflow.model;

import com.hitotek.workflow.constant.Messages;
import com.hitotek.workflow.model.multipart.KeyConverter;
import com.hitotek.workflow.util.DateUtils;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.*;

/**
 * 回调消息模型
 * 链式调用可以方便的设置回调的参数
 *
 * @author Qicheng Peng
 */
@SuppressWarnings("unchecked")
public class Data extends HashMap<String, Object> {
    private KeyConverter converter = new KeyConverter();

    /**
     * 添加参数
     *
     * @param key   键
     * @param value 目标值
     * @return 当前类
     */
    public Data include(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 复写get方法 将命名法转化后进行获取
     *
     * @param key 目标key 可以是下划线命名法或驼峰
     * @return 数据
     */
    public Object get(String key) {
        if (super.get(converter.convert(key)) != null) {
            return super.get(converter.convert(key));
        } else {
            return super.get(converter.convertLine(key));
        }
    }

    /**
     * 获得数据并转化为对应类型
     *
     * @param key    目标key
     * @param target 目标类型
     * @param <T>    目标泛型
     * @return 数据
     */
    public <T> T getPart(String key, Class<T> target) {
        if (!isNull(key)) {
            Object o = get(key);
            if (o.getClass().equals(target) || target.isAssignableFrom(o.getClass())) {
                return (T) o;
            } else {
                if (target.isAssignableFrom(o.getClass())) {
                    return (T) o;
                } else {
                    classCanNotCastException(o.getClass(), target);
                }
            }
        }
        return null;
    }

    /**
     * 配合Java 8 Optional使用
     *
     * @param key    目标Key
     * @param target 目标类型
     * @param <T>    目标泛型
     * @return 目标
     */
    public <T> Optional<T> getOptionalPart(String key, Class<T> target) {
        return Optional.ofNullable(getPart(key, target));
    }


    /**
     * 从map中获得目标类型的数据
     *
     * @param tClass 类型
     * @param <T>    泛型
     * @return 数据
     */
    public <T> T get(Class<T> tClass) {
        Field[] declaredFields = tClass.getDeclaredFields();
        try {
            T t = tClass.newInstance();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                Object val = get(declaredField.getName());
                if (val != null && declaredField.getType().isAssignableFrom(val.getClass())) {
                    declaredField.set(t, val);
                }
                declaredField.setAccessible(false);
            }
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }


    /**
     * @return 快捷获取相当于 get（"data"）
     */
    public Object getData() {
        return get(Messages.KEY_DATA);
    }

    /**
     * @return 快捷获取相当于 getPart("data", tClass);
     */
    public <T> T getData(Class<T> tClass) {
        return getPart(Messages.KEY_DATA, tClass);
    }

    /**
     * 判定目标key是否空
     *
     * @param key key
     * @return 是否空
     */
    private Boolean isNull(String key) {
        return this.get(key) == null;
    }

    /**
     * 获得数据并转化为String
     *
     * @param key key
     * @return 转化过后的值
     */
    public String getStringPart(String key) {
        String part = getPart(key, String.class);
        if (part == null) {
            return "";
        }
        return part;
    }

    public Optional<String> getStringOptionalPart(String key) {
        return Optional.ofNullable(this.getStringPart(key));
    }

    /**
     * 获得数据并转化为Float
     *
     * @param key
     * @return
     */
    public Float getFloatPart(String key) {
        return getPart(key, Float.class);
    }

    /**
     * 获得数据并转化为Boolean
     *
     * @param key key
     * @return 转化过后的值
     */
    public Boolean getBooleanPart(String key) {
        return getPart(key, Boolean.class);
    }

    /**
     * 获得数据并转化为Integer
     *
     * @param key key
     * @return 转化过后的值
     */
    public Integer getIntegerPart(String key) {
        return getPart(key, Integer.class);
    }

    /**
     * 获得数据并转化为 Long
     * @param key key
     * @return 转化过后的值
     */
    public Long getLongPart(String key) {
        return getPart(key, Long.class);
    }

    /**
     * 获得数据并转化为Integer
     *
     * @param key key
     * @return 转化过后的值
     */
    public Optional<Integer> getIntegerOptionalPart(String key) {
        return Optional.ofNullable(this.getIntegerPart(key));
    }

    /**
     * 获得数据并转化为Double
     *
     * @param key key
     * @return 转化过后的值
     */
    public Double getDoublePart(String key) {
        return getPart(key, Double.class);
    }

    /**
     * 获得数据并转化为Date
     *
     * @param key key
     * @return 转化过后的值
     */
    public Date getDatePart(String key) {
        Object o = get(key);
        if (o instanceof String) {
            return DateUtils.from(getStringPart(key));
        } else if (o instanceof Timestamp) {
            Timestamp timestamp = (Timestamp) o;
            return DateUtils.timestamp2Date(timestamp.getTime() / 1000);
        } else {
            return getPart(key, Date.class);
        }
    }

    private void classCanNotCastException(Class current, Class target) {
        throw new ClassCastException(current.getName() + " cant't cast to " + target.getName());
    }

    /**
     * 获得数据并转化为Map
     *
     * @param key key
     * @return 转化过后的值
     */
    public <K, V> Map<K, V> getMapPart(String key, Class<K> keyType, Class<V> valueType) {
        Object o = get(key);
        if (o instanceof Map) {
            Map map = (Map) o;
            if (map.size() > 0) {
                if (map.keySet().toArray()[0].getClass().equals(keyType)) {
                    // 检查
                    map.forEach((k, v) -> {
                        if (!k.getClass().equals(keyType) && !keyType.isAssignableFrom(k.getClass())) {
                            classCanNotCastException(k.getClass(), keyType);
                        }
                        if (!v.getClass().equals(valueType) && !valueType.isAssignableFrom(v.getClass())) {
                            classCanNotCastException(v.getClass(), valueType);
                        }
                    });
                    return (Map<K, V>) o;
                }
            } else {
                return null;
            }
        } else {
            classCanNotCastException(o.getClass(), Map.class);
        }
        return new HashMap<>();
    }

    /**
     * 获得数据并转化为List
     *
     * @param key key
     * @return 转化过后的值
     */
    public <T> List<T> getListPart(String key, Class<T> tClass) {
        if (!isNull(key)) {
            Object o = get(key);
            if (o instanceof List) {
                List list = (List) o;
                if (list.size() > 0) {
                    if (list.get(0).getClass().equals(tClass)) {
                        return (List<T>) list;
                    }
                }
                return list;
            } else {
                List list = new ArrayList();
                list.add(o);
                return list;
            }
        }
        // 避免空指针
        return new ArrayList<>();
    }

    /**
     * 获得数据并转化为Set
     *
     * @param key key
     * @return 转化过后的值
     */
    public <T> Set<T> getSetPart(String key, Class<T> tClass) {
        if (!isNull(key)) {
            Object o = get(key);
            if (o instanceof Set) {
                Set list = (Set) o;
                if (list.size() > 0) {
                    if (list.toArray()[0].getClass().equals(tClass)) {
                        return (Set<T>) list;
                    }
                }
                return list;
            } else {
                Set list = new HashSet<>();
                list.add(o);
                return list;
            }
        }
        return new HashSet<>();
    }


    /**
     * 移除值
     *
     * @param key 键
     * @return 当前类
     */
    public Data exclude(String key) {
        super.remove(converter.convert(key));
        super.remove(converter.convertLine(key));
        return this;
    }

    /**
     * 添加消息
     *
     * @param msg 消息内容
     * @return 当前类
     */
    public Data message(String msg) {
        this.include(Messages.KEY_MSG, msg);
        return this;
    }

    public Data code(Integer code) {
        this.include(Messages.KEY_CODE, code);
        return this;
    }

    /**
     * 添加数据
     *
     * @param data 数据内容
     * @return 当前类
     */
    public Data data(Object data) {
        this.include(Messages.KEY_DATA, data);
        return this;
    }
}
