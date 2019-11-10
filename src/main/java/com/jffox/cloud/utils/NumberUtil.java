package com.jffox.cloud.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class NumberUtil {

	/**
	 * 为空返回0，否则转为Double类型
	 * 
	 * @param num
	 * @return
	 */
	public static Double toDouble(String num) {
		if (StringUtil.isBlank(num)) {
			return 0.0;
		} else {
			return Double.parseDouble(num);
		}
	}
	
	/**
	 * 为空返回0，否则转为Double类型
	 * 
	 * @param num
	 * @return
	 */
	public static Double toDouble(Object num) {
		if (StringUtil.isBlank(num)) {
			return 0.0;
		} else {
			return Double.parseDouble(num+"");
		}
	}
	
	
	public static Double toDouble(Double num) {
		if (StringUtil.isBlank(num)) {
			return 0.0;
		} else {
			return num ;
		}
	}
	
	/**
	 * 为空返回0，否则转为Long类型
	 * 
	 * @param num
	 * @return
	 */
	public static Long toLong(String num) {
		if (StringUtil.isBlank(num)) {
			return 0l;
		} else {
			return Long.parseLong(num);
		}
	}


	/**
	 * 为空返回0，否则转为Integer类型
	 * 
	 * @param num
	 * @return
	 */
	public static Integer toInteger(String num) {
		if (StringUtil.isBlank(num)) {
			return 0;
		} else {
			BigDecimal bd = new BigDecimal(num).setScale(0,
					BigDecimal.ROUND_HALF_UP);
			return Integer.parseInt(bd.toString());
		}
	}

	/**
	 * 两个Integer相除
	 * @param a
	 * @param b
	 * @return
	 */
	public static String divInteger(Integer a,Integer b){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format((float)a/b);
	}

	public static String divBigDecimal(BigDecimal a,BigDecimal b){
		if(a == null || b == null || BigDecimal.ZERO.equals(b)) {
			return "0";
		}else {
			return String.valueOf(a.divide(b,2,BigDecimal.ROUND_HALF_UP));
		}
	}

	public static String divBigDecimal(String a,String b){
		return divBigDecimal(a,b,2);
	}

	/**
	 * 整数相除，默认保留两位小数
	 * @param a
	 * @param b
	 * @return
	 */
	public static String divBigDecimal(Integer a,Integer b){
		return divBigDecimal(a,b,2);
	}

	/**
	 * 两个整数相除
	 * @param a
	 * @param b
	 * @param num
	 * @return
	 */
	public static String divBigDecimal(Integer a,Integer b,int num){
		if(b == null || b == 0) {
			return null;
		}else {
			BigDecimal aBig = new BigDecimal(a);
			BigDecimal bBig = new BigDecimal(b);
			return String.valueOf(aBig.divide(bBig,num,BigDecimal.ROUND_HALF_UP));
		}
	}

	/**
	 * 两个数相除
	 * @param a
	 * @param b
	 * @param num
	 * @return
	 */
	public static String divBigDecimal(String a,String b,int num){
		if(StringUtils.isBlank(a) || StringUtils.isBlank(b) || "0".equals(b)) {
			return null;
		}else {
			BigDecimal aBig = new BigDecimal(a.trim());
			BigDecimal bBig = new BigDecimal(b.trim());
			return String.valueOf(aBig.divide(bBig,num,BigDecimal.ROUND_HALF_UP));
		}
	}

	/**
	 * 求和
	 * @param list
	 * @return
	 */
	public static Integer sum(List<Integer> list) {
		if (list == null || list.size()==0)
			return 0;
		Integer result = 0;
		for (Integer i : list) {
			result += i;
		}
		return result;
	}

	/**
	 * 求平均值
	 * @param list
	 * @return
	 */
	public static String avg(List<Integer> list){
		if(list == null || list.size() == 0){
			return "0";
		}
		BigDecimal sumBig = new BigDecimal(sum(list));
		return String.valueOf(sumBig.divide(new BigDecimal(list.size()),2,BigDecimal.ROUND_HALF_UP));
	}

	public static Integer min(List<Integer> list){
		if(list == null || list.size() == 0) return null;
		return Collections.min(list);
	}

	public static Integer max(List<Integer> list){
		if(list == null || list.size() == 0) return null;
		return Collections.max(list);
	}
}
