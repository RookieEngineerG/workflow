package com.hitotek.workflow.util;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @Description 进步一简化分页帮助类的操作
 * @date Create at 2018/7/27
 */
public class PageUtils {
    private static Pages pages = new Pages(0);

    private PageUtils() {
    }

    /**
     * 取得分页后的总页码
     *
     * @param singleMax 单页记录数量
     * @param totalMax  数据库总量
     * @return 分页后的页面数量
     */
    public static int count(int singleMax, int totalMax) {
        pages.setSingleMax(singleMax);
        return pages.count(totalMax);
    }

    /**
     * @param singleMax 单页记录数量
     * @param index     需要跳转到的页码
     * @return limit函数对应的值
     */
    public static int limit(int singleMax, int index) {
        pages.setSingleMax(singleMax);
        return pages.index(index);
    }
}
