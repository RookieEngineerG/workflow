package com.hitotek.workflow.util;

/**
 * @author Qicheng Peng
 * 分页工具类
 * Create at 2018/7/27
 */
public class Pages {
    private int singleMax;

    /**
     * 构造器
     *
     * @param singleMax 指定分页返回的数据量
     */
    public Pages(int singleMax) {
        this.singleMax = singleMax;
    }

    public void setSingleMax(int singleMax) {
        this.singleMax = singleMax;
    }

    /**
     * 计算 mysql limit 函数对应的分页值
     *
     * @param index 用户的分页请求 1,2,3....
     * @return limit 函数对应的分页值
     */
    public int index(int index) {
        if (index == 0) {
            return 0;
        }
        return singleMax * (index - 1);
    }

    /**
     * 分页总页数的方法
     *
     * @param totalCount 记录的总数量
     * @return 分页的总数
     */
    public int count(int totalCount) {
        // 拿到数据库记录数量
        // 如果当前总页数量大于 singleMax 则进行下一步否则直接返回 1
        if (totalCount / singleMax != 0) {
            // 如果最大页数可以被singleMax整除则直接返回
            if (totalCount % singleMax == 0) {
                return totalCount / singleMax;
            } else {
                // 不被singleMax整除的情况 多出额外的一页
                return totalCount / singleMax + 1;
            }
        }
        return totalCount > 0 ? 1 : 0;
    }
}
