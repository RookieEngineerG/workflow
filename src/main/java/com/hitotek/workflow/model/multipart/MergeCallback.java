package com.hitotek.workflow.model.multipart;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description 合并数据时的回调
 * @date 2019/1/9
 */
public interface MergeCallback {
    /**
     * 一个值第一次出现需要合并的时候
     *
     * @param part           key
     * @param multipartData 结果集
     */
    public void onMergeAppear(Part part, MultipartData multipartData);

    /**
     * 每次合并(与onMergeAppear不冲突)都会调用的接口
     *
     * @param key           key
     * @param value         值
     * @param multipartData 结果集
     */
    public void onMerge(String key, Object value, MultipartData multipartData);

    /**
     * 每次合并完成都会调用的结果
     *
     * @param multipartData 结果集
     */
    public void afterMerge(MultipartData multipartData);

    /**
     * 合并彻底完成后调用的接口
     * @param target 完成合并后的数据
     */
    public void completeMerge(MultipartData target);
}
