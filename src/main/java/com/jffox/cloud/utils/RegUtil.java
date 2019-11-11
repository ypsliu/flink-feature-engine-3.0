package com.jffox.cloud.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bianfulin
 * @version 1.0
 * @date 2018-01-01
 * @describe 正则工具类
 * @since jdk 1.8
 */
public class RegUtil {

    // private static String is_overdue = "(?<=已).{0,3}?(?=逾期)";
    /**
     * 判断content是否包含“已***逾期”
     */
    public static String is_overdue = ".*已.{0,3}逾期.*|.*已.{0,3}违约.*|.*已.{0,3}拖欠.*";
    /**
     * 是否包含“消费人民币”
     */
    public static String is_credit_consume = ".*消费人民币.*";
    /**
     * 截取“天”前面的数字
     */
    public static String overdue_day = "\\d{1,3}?(?=天)";
    /**
     * 截取“元”前面的数字
     */
    public static String overdue_money = "([1-9]\\d{0,8}(\\.\\d{1,2})?)(?=元)";
    /**
     * 截取“余额”或“余额+1字符”后面的数字
     */
    public static String balance_money = "(?<=余额.?)([1-9]\\d{0,8}(\\.\\d{1,2})?)";
    /**
     * 贷款类app
     */
    public static String load_app = ".*借.*|.*贷.*";
    /**
     * []或【】中的字符
     */
    public static String bracket_inside = "(?<=【).*(?=】)|(?<=\\[).*(?=\\])";
    // public static String bracket_inside = "(?<=\\[).*(?=\\])";
    /**
     * 去除银行前的非中文字符
     */
    public static String bank_sub = "[^\\x00-\\xff]{0,9}银行";
    /**
     * 判断银行短信
     */
    public static String bank_note = "[^\\x00-\\xff]{1,5}银行|[^\\x00-\\xff]{1,5}农信|[^\\x00-\\xff]{0,5}信合|[^\\x00-\\xff]{0,5}信用合作社";
    /**
     * 判断是否是营销类短信
     */
    public static String marketing_note = "退订|退定|td|回T";
    /**
     * 不为空和空字符串
     */
    public static String not_nullStr_and_blank = "[^\\s]{1,}";
    /**
     * 提取月份
     */
    public static String month = ".*\\d{1,2}月.*";
    /**
     * 提取金额
     */
    public static String money = "(([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(\\.[0-9]{1,2})?(?=元))|((?<=人民币)([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(\\.[0-9]{1,2})?)";
    /**
     * 小蓝卡验证码
     */
    public static String verfiy_code = "(?<![0-9])(([0-9]{4})|([0-9]{6}))(?![0-9])";
    /**
     * 只包含数字，最少一位
     */
    public static String digit = "\\d+";

    /**
     * HH:mm:ss
     */
    private static String time = "\\d{2}:\\d{2}:\\d{2}";
    /**
     * yyyy-MM-dd
     */
    private static String date = "\\d{4}-\\d{2}-\\d{2}";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    private static String timestamp = date + "\\s" + time;

    public static void main(String[] args) {
        String str = "2323x4234";
        System.out.println(RegUtil.isMatch(str, RegUtil.digit));
    }

    /**
     * - 获取查询的字符串 将匹配的字符串取出
     */
    public static List<String> getStringList(String str, String regx) {
        List<String> result = new ArrayList<>();
        // 1.将正在表达式封装成对象Patten 类来实现
        Pattern pattern = Pattern.compile(regx);
        // 2.将字符串和正则表达式相关联
        Matcher matcher = pattern.matcher(str);
        // 3.String 对象中的matches 方法就是通过这个Matcher和pattern来实现的。
        // 查找符合规则的子串
        while (matcher.find()) {
            // 获取 字符串
            result.add(matcher.group());
        }
        return result;
    }

    /**
     * 获取查询的字符串 将匹配的字符串取出
     */
    public static String getString(String str, String regx) {
        // 1.将正在表达式封装成对象Patten 类来实现
        Pattern pattern = Pattern.compile(regx);
        // 2.将字符串和正则表达式相关联
        Matcher matcher = pattern.matcher(str);
        // 3.String 对象中的matches 方法就是通过这个Matcher和pattern来实现的。
        // 查找符合规则的子串

        while (matcher.find()) {
            // 获取 字符串
            return matcher.group();
        }
        return null;
    }

    /**
     * @param str
     * @param regx1 外层
     * @param regx2 内层
     * @return
     */
    public static String getStringWith2Exp(String str, String regx1,
                                           String regx2) {
        return RegUtil.getString(RegUtil.getString(str, regx1), regx2);
    }

    /**
     * 判断是否匹配
     *
     * @param str
     * @param regx
     * @return
     */
    public static boolean isMatch(String str, String regx) {
        if (StringUtil.isBlankStr(str) || StringUtil.isBlankStr(str))
            return false;
        // 1.将正在表达式封装成对象Patten 类来实现
        Pattern pattern = Pattern.compile(regx);
        // 2.将字符串和正则表达式相关联
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断是否包含该字符串
     *
     * @param str
     * @param regx
     * @return
     */
    public static boolean isInclude(String str, String regx) {
        if (StringUtil.isBlankStr(str) || StringUtil.isBlankStr(str))
            return false;
        // 1.将正在表达式封装成对象Patten 类来实现
        Pattern pattern = Pattern.compile(regx);
        // 2.将字符串和正则表达式相关联
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * 字符串的分割
     */
    public static void getDivision(String str, String regx) {
        String[] dataStr = str.split(regx);
        for (String s : dataStr) {
            System.out.println("正则表达式分割++" + s);
        }
    }

    /**
     * 字符串的替换
     */
    public static void getReplace(String str, String regx, String replaceStr) {
        String stri = str.replaceAll(regx, replaceStr);
        System.out.println("正则表达式替换" + stri);
    }

    /**
     * 字符串处理之匹配 String类中的match 方法
     */
    public void getMatch(String str, String regx) {
        System.out.println("正则表达匹配" + str.matches(regx));
    }
}
