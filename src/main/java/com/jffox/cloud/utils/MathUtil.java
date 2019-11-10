package com.jffox.cloud.utils;


import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 数学计算类
 * 
 * @author bianfulin
 *
 */
public class MathUtil {

	/**
	 * 除法，分母为零或空时，返回null,保留小数点四位
	 * 
	 * @param a
	 * @param b
	 *            分母
	 * @return
	 */
	public static Double div(Object a, Object b) {
		if (a == null || b == null)
			return 0.0;
		if(StringUtil.isBlankStr((String)a)||StringUtil.isBlankStr((String)b)){
			return 0.0;
		}
		if (NumberUtils.isNumber(b.toString())
				&& Double.valueOf(b.toString()) == 0) {
			return 0.0;
		}
		return Double.valueOf(a.toString()) / Double.valueOf(b.toString());
	}

	/**
	 * 保留scale位小数
	 * 
	 * @param d
	 * @param scale
	 * @return
	 */
	public static String format(Double d, int scale) {
		String s = "";
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMaximumFractionDigits(scale);
		format.setMinimumFractionDigits(scale);
		format.setGroupingUsed(false);
		if (d != null) {
			s = format.format(d);
		}
		return s;

	}

	// 方差s^2=[(x1-x)^2 +...(xn-x)^2]/n
	public static Double variance(Double[] x) {
		if (x == null || x.length == 0)
			return null;
		int m = x.length;
		Double sum = 0.0;
		for (int i = 0; i < m; i++) {// 求和
			sum += x[i];
		}
		Double dAve = sum / m;// 求平均值
		Double dVar = 0.0;
		for (int i = 0; i < m; i++) {// 求方差
			dVar += (x[i] - dAve) * (x[i] - dAve);
		}
		return MathUtil.div(dVar, m);
	}

	// 方差s^2=[(x1-x)^2 +...(xn-x)^2]/n
	public static Double variance(List<Double> x) {
		if (ListUtil.isEmpty(x)) {
			return 0.0;
		}
		return variance(x.toArray(new Double[0]));
	}

	// 标准差σ=sqrt(s^2)
	public static Double standardDiviation(List<Double> x) {
		if (ListUtil.isEmpty(x)) {
			return 0.0;
		}
		return standardDiviation(x.toArray(new Double[0]));
	}

	// 标准差σ=sqrt(s^2)
	public static Double standardDiviation1(List<Double> x) {
		if (ListUtil.isEmpty(x)) {
			return null;
		}
		return standardDiviation(x.toArray(new Double[0]));
	}

	// 标准差σ=sqrt(s^2)
	public static Double standardDiviation(Double[] x) {
		int m = x.length;
		Double sum = 0.0;
		for (int i = 0; i < m; i++) {// 求和
			sum += x[i];
		}
		Double dAve = sum / m;// 求平均值
		Double dVar = 0.0;
		for (int i = 0; i < m; i++) {// 求方差
			dVar += (x[i] - dAve) * (x[i] - dAve);
		}
		if (m == 0)
			return null;
		return Math.sqrt(dVar / m);
	}

	// 标准差σ=sqrt(s^2)
	public static Double standardDiviation(double[] x) {
		int m = x.length;
		Double sum = 0.0;
		for (int i = 0; i < m; i++) {// 求和
			sum += x[i];
		}
		Double dAve = sum / m;// 求平均值
		Double dVar = 0.0;
		for (int i = 0; i < m; i++) {// 求方差
			dVar += (x[i] - dAve) * (x[i] - dAve);
		}
		if (m == 0)
			return null;
		return Math.sqrt(dVar / m);
	}

	public static Double sum(List<Double> list) {
		if (ListUtil.isEmpty(list))
			return 0.0;
		Double result = 0.0;
		for (Double d : list) {
			result += d;
		}
		return result;
	}


	public static <T> Double sum(Collection<Double> list ) {
		if (ListUtil.isEmpty(list))
			return 0.0;
		Double result = 0.0;
		for (Double d : list) {
			result += d;
		}
		return result;
	}

	public static Double max(Collection<Double> list) {
		if (ListUtil.isEmpty(list))
			return 0.0;
		return Collections.max(list);
	}

	/**
	 * 求list最大值
	 * 
	 * @param list
	 * @param clazz
	 *            泛型类型
	 * @return
	 */
	public static <T extends Object & Comparable<? super T>> T max(
			Collection<? extends T> list, Class<? extends Comparable<? super T>> clazz) {
		if (ListUtil.isEmpty(list)) {
			return returnDefault(list, clazz);
		}
		return Collections.max(list);
	}

	/**
	 * 返回默认值逻辑
	 * 
	 * @param list
	 * @param clazz
	 * @return
	 */
	private static <T extends Comparable<? super T>> T returnDefault(
			Collection<? extends T> list, Class<?> clazz) {
		if (ListUtil.isEmpty(list)) {
			if (clazz == Double.class) {
				return (T) new Double(0.0);
			} else if (clazz == Integer.class) {
				return (T) new Integer(0);
			} else if (clazz == Long.class) {
				// return (T) new Long(0);
				return null;
			}
		}
		return null;
	}

	public static Double min(List<Double> list) {
		if (ListUtil.isEmpty(list))
			return 0.0;
		return Collections.min(list);
	}

	/**
	 * 求list最大值
	 * 
	 * @param list
	 * @param clazz
	 *            泛型类型
	 * @return
	 */
	public static <T extends Object & Comparable<? super T>> T min(
			Collection<? extends T> list, Class<? extends Comparable<? super T>> clazz) {
		if (ListUtil.isEmpty(list)) {
			return returnDefault(list, clazz);
		}
		return Collections.min(list);
	}

	public static Double avg(List<Double> list) {
		if (ListUtil.isEmpty(list))
			return 0.0;
		Double result = sum(list);
		return MathUtil.div(result, list.size());
	}

	public static BigDecimal avgBigDecimal(List<BigDecimal> list) {
		if (ListUtil.isEmpty(list))
			return BigDecimal.ZERO;
		BigDecimal result = sumBigDecimal(list);
		return BigDecimal.valueOf(MathUtil.div(result, list.size()));
	}

	public static BigDecimal sumBigDecimal(List<BigDecimal> list) {
		if (ListUtil.isEmpty(list))
			return null;
		BigDecimal result = new BigDecimal("0");
		for (BigDecimal b : list) {
			result = result.add(b);
		}
		return result;
	}
	/**
	 * 累加
	 * @param num
	 * @return
	 */
	public static Double incr(Double num) {
		if (num==null)
			return 1.0;
		return num++;
	}

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(new Integer[] {});
		Integer maxTest = MathUtil.max(list, Integer.class);
		System.out.println(maxTest);
	}
}
