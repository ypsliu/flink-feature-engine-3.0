package com.jffox.cloud.utils;

import java.util.Collection;
import java.util.List;

/**
 * @author yanggang
 * @version 1.0
 * @date 2019-09-01
 * @describe list数组工具类
 * @since jdk 1.8
 */
public class ListUtil {

    /**
     * 为空或size为零返回true
     *
     * @param list
     * @return
     */
    public static <T> boolean isEmpty(Collection<T> list) {
        if (list != null && list.size() != 0) return false;
        return true;
    }

    /**
     * 不为空返回true
     *
     * @param list
     * @return
     */
    public static <T> boolean isNotEmpty(Collection<T> list) {
        return !isEmpty(list);
    }

    public static void main(String[] args) {
        List a = null;
//		a.add(1);
        System.out.println(ListUtil.isNotEmpty(a));
        System.out.println(ListUtil.isEmpty(a));

    }
}
