package com.cancer.dynamic.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.datasource
 * @Description:
 * @date 2018/5/1713:49
 */
public class DynamicDataSourceContextHolder  {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static List<String> dataSourceIds = new ArrayList<>();

    public static void setDataSource(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }

    /**
     * 判断指定DataSrouce当前是否存在
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
}
