package com.jffox.cloud.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class StringUtil {

    private static final String EMPTY = "";
    private static String reg = "^(\\-|\\+)?\\d+(\\.\\d+)?$";


    /**
     * 判断是否是window系统
     *
     * @return
     */
    public static boolean isWindowOS() {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS == null) return false;
        if (OS.contains("windows")) {
            return true;
        }
        return false;
    }


    /**
     * Returns the first sub string after target and exclude target.
     *
     * @param str
     * @param target
     * @return String
     */
    public static String afterString(String str, String target) {
        //
        if (str == null || target == null) {
            return str;
        }
        //
        int begin = str.indexOf(target);
        if (begin < 0) {
            return str;
        }
        //
        return str.substring(begin + target.length());
    }

    public static String objtostring(Object obj) {
        String result = "";
        if (!isBlank(obj)) {
            result = obj.toString();
        }
        return result;
    }

    public static Integer Stringtoint(String obj) {
        int result = 0;
        if (!isBlankStr(obj)) {
            result = Integer.parseInt(obj);
        }
        return result;
    }

    /**
     * Returns the first sub string before target and exclude target.
     *
     * @param str
     * @param target
     * @return String
     */
    public static String beforeString(String str, String target) {
        //
        if (str == null || target == null) {
            return str;
        }
        //
        int begin = str.indexOf(target);
        if (begin < 0) {
            return str;
        }
        //
        return str.substring(0, begin);
    }

    /**
     * Returns object real class and hash code.
     *
     * @return String
     */
    public static String toString(Object obj) {
        //
        return obj.getClass().getName() + "@" + Integer.toHexString(obj.hashCode());
    }

    /**
     * Returns if a string is real blank.
     *
     * @param target
     * @return boolean
     */
    public static boolean isBlank(Object target) {
        return (target == null || "".equals(target.toString().trim()));
    }

    public static boolean isBlankStr(String target) {
        return (target == null || "".equals(target.trim())) || "NULL".equals(target.trim().toUpperCase());
    }

    public static boolean isNotBlankStr(String target) {
        return !isBlankStr(target);
    }

    public static boolean isPureDigital(String target) {

        if (target != null) {
            return target.matches(reg) ? true : false;
        } else {
            return false;
        }
    }

/*    public static void main(String[] args) {
        String regs = "^[A-Za-z0-9]{5}";
        String s = "01090_700000000114734498 column=f:riskExplain_list, timestamp=1490860284808, value=\\xE9\\x80\\xBE\\xE6\\x9C\\x9F 1 \\xE6\\x9C\\x9F";
        System.out.println(s.matches(regs));
    }*/

    /**
     * Returns string compare result.
     *
     * @param str1
     * @param str2
     * @return boolean
     */
    public static boolean compare(String str1, String str2) {
        //
        return (str1 != null ? str1.equals(str2) : (str2 == null));
    }

    public static String join(Object[] array) {
        return join(array, null);
    }

    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }

        int bufSize = (endIndex - startIndex);
        if (bufSize <= 0) {
            return EMPTY;
        }

        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length())
                + separator.length());

        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    public static String nullWithDefault(Object value, String defaultStr) {
        return value == null ? defaultStr : String.valueOf(value);
    }

    /**
     * 样例：s:{'object':{'code':'1111':'12','desc':'三维验证一致',
     * 'city':'a','isp':'移动','province':'hh'},'spendTime':32,'transNo':'geo_1487434707610_HFOUkp','startTime':1487434707610,'status':1,}}
     * tarstring：code
     *
     * @param s
     * @param tarstring
     * @return
     */
    public static String returnsubstring(String s, String tarstring) {
        String result = null;
        if (s.contains(tarstring)) {
            result = s.substring(s.indexOf(":", s.indexOf(tarstring)) + 1 + 1, s.indexOf("'", s.indexOf(":", s.indexOf(tarstring)) + 1 + 1));
//    	 result=s.substring(s.indexOf(":", s.indexOf(tarstring))+1+1, s.indexOf("\"",s.indexOf("\"", s.indexOf(tarstring))+1+1)); 	   	

        }
        return result;
    }

    /**
     * 正则表达式匹配相应值截取
     * 样例str：
     *
     * @param str:{\"object\":{\"city\":\"株洲\",\"code\":\"0\",\"desc\":\"三维验证一致\",\"isp\":\"移动\",\"province\":\"湖南\"},\"spendTime\":37,\"startTime\":1487434707610,\"status\":1,\"transNo\":\"geo_1487434707610_HF0Ukp\"}" 或者：{'object':{'code':'1111':'12','desc':'三维验证一致', 'city':'a','isp':'移动','province':'hh'},'spendTime':32,'transNo':'geo_1487434707610_HFOUkp','startTime':1487434707610,'status':1,}}"
     * @param tarsre                                                                                                                                                                                                       要匹配的值，类型string，如code，city，statis
     * @return
     */
    public static String regexstr(String str, String tarsre) {
        String result = "";
        Pattern p = Pattern.compile("[\"|']" + tarsre + "[\"|']:[\"|'|[0-9]].*?[\"|'|,]");
        Matcher m = p.matcher(str);
        while (m.find()) {
            if (StringUtil.isBlankStr(m.group(0))) continue;
            String splitvalue = m.group(0).split(":")[1];
            if (splitvalue != null) {
                if (splitvalue.endsWith(",")) {
                    result = splitvalue.substring(0, splitvalue.length() - 1);
//					System.out.println("--"+splitvalue.substring(0, splitvalue.length()-1));
                } else {
                    result = splitvalue.substring(1, splitvalue.length() - 1);
//					System.out.println("--"+splitvalue.substring(1, splitvalue.length()-1));
                }
            }
        }
        return result;
    }

    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //"将文件名中的汉字转为UTF8编码的串时错误，输入的字符串为：" + s);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    public static String spliceStr(String old, String param, String separator) {
        if (StringUtils.isBlank(old)) return param;
        if (StringUtils.isNotBlank(param)) {
            boolean isContains = false;
            String[] arr = old.split(separator);
            for (String str : arr) {
                if (param.equals(str)) {
                    isContains = true;
                }
            }
            if (!isContains) {
                old = old + separator + param;
            }
        }
        return old;
    }

    /**
     * 包含List中所有字符串
     *
     * @param content
     * @param strs
     * @return
     */
    public static Boolean contain(String content, List<String> strs) {
        if (content == null || ListUtil.isEmpty(strs)) {
            return false;
        }
        for (String str : strs) {
            if (!content.contains(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 包含List中一个字符串即可
     *
     * @param content
     * @param strs
     * @return
     */
    public static Boolean containOne(String content, List<String> strs) {
        if (content == null || ListUtil.isEmpty(strs)) {
            return false;
        }
        for (String str : strs) {
            if (content.contains(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 不包含List中所有字符串
     *
     * @param content
     * @param strs
     * @return
     */
    public static Boolean notContain(String content, List<String> strs) {
        if (content == null || ListUtil.isEmpty(strs)) {
            return false;
        }
        for (String str : strs) {
            if (content.contains(str)) {
                return false;
            }
        }
        return true;
    }

    //判断String转为double后是否大于给定的值
    public static Boolean isLargerThen(String dataStr, double da) {
        boolean res = false;
        if (StringUtil.isNotBlankStr(dataStr)) {
            try {
                double v = Double.parseDouble(dataStr);
                if (v > da) {
                    res = true;
                }
            } catch (NumberFormatException e) {
            }
        }
        return res;
    }

}