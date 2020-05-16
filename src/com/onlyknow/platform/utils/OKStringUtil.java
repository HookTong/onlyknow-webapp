package com.onlyknow.platform.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OKStringUtil {

    /**
     * @param strString
     * @param strLength 目标长度
     * @description 字符串根据长度左补零
     */
    public static String addLeftZero(String strString, int strLength) {
        int strLen = strString.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer strBuf = new StringBuffer();
                strBuf.append("0").append(strString);
                strString = strBuf.toString();
                strLen = strString.length();
            }
        }
        return strString;
    }

    /**
     * @param strString
     * @return String
     * @Description 减去字符串最后一个字符
     * @author shidong.zhao
     * @date 2016-7-27 上午11:16:32
     */
    public static String subLastChar(String strString) {
        if (isBlank(strString)) {
            return null;
        } else {
            return strString.substring(0, strString.length() - 1);
        }
    }

    /**
     * @param commaStr
     * @return String
     * @Description 逗号分隔的字符串，转成sql的字符串
     * @author shidong.zhao
     * @date 2016-8-10 下午6:01:05
     */
    public static String commaStrToSqlStr(String commaStr) {
        String sqlStr = "";
        if (isNotBlank(commaStr)) {
            String[] buildingIdArr = commaStr.split(",");
            String newBuildingIds = "";
            for (String buildingId : buildingIdArr) {
                newBuildingIds += "\"" + buildingId + "\",";
            }
            sqlStr += OKStringUtil.subLastChar(newBuildingIds);
        }
        return sqlStr;
    }

    public static String deDivideMark(String strString, String divideMark) {
        if ((strString.substring(strString.length() - 1, strString.length())).equals(divideMark)) {
            return strString.substring(0, strString.length() - 1);
        }
        return strString;
    }


    public static String notEmpty(Object obj, String defautValue) {
        if (obj == null) {
            return defautValue;
        }
        return obj.toString();
    }

    public static String notBlank(Object obj, String defautValue) {
        if (obj == null || "".equals(obj.toString())) {
            return defautValue;
        }
        return obj.toString();
    }


    public static List<String> split(String str, String mark, boolean removeEmpty) {
        List<String> list = new ArrayList<String>();
        if (isEmpty(str)) {
            return list;
        }

        String[] temps = str.split(mark);
        for (String s : temps) {
            if (removeEmpty && "".equals(s)) {
                continue;
            }
            list.add(s);
        }
        return list;
    }

    /**
     * @param str
     * @return List<String>
     * @Description 字符串用逗号分隔
     * @author shidong.zhao
     * @date 2016-7-14 下午3:04:48
     */
    public static List<String> splitByComma(String str) {
        List<String> list = OKStringUtil.split(str, ",", true);
        return list;
    }

    public static String join(List<String> list, String mark) {
        String ret = "";
        for (String s : list) {
            if (ret.length() > 0) ret += mark;
            ret += s;
        }
        return ret;
    }

    public static String formatDoubleToMoney(Double v) {
        if (v == null) {
            return "";
        }
        DecimalFormat df = new DecimalFormat("0.##");
        return df.format(v);
    }

    public static boolean checkIsNumber(String obj) {

        if (obj == null) return false;
        char ret[] = obj.trim().toCharArray();
        for (char c : ret) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIsMoney(String obj) {
        Pattern pattern = Pattern.compile("^([0-9])*(\\.[0-9]{0,2})?$");
        Matcher matcher = pattern.matcher(obj);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符串处理
     * if null return ""
     * else return str.trim()
     *
     * @param str
     * @return
     */
    public static String notNull(String str) {
        if (null == str)
            return "";
        return str.trim();
    }


    //得到一个格式化的文件大小，最多保留两位小数
    public static String formatFileSize(Long size) {
        if (size == null) return "0 Byte";
        if (size <= 0)
            return "0 Byte";
        if (size >= 1024 && size < 1048576)
            return (Math.round(size / 1024 * 100) / 100) + " KB";
        if (size >= 1048576 && size < 1073741842)
            return (Math.round(size / 1048576 * 100) / 100) + " MB";
        if (size >= 1073741842 && size < 1099511627776L)
            return (Math.round(size / 1073741842 * 100) / 100) + " GB";
        if (size >= 1099511627776L)
            return (Math.round(size / 1099511627776L * 100) / 100) + " TB";
        return size + " B";
    }

    /***
     * 数值单位转换
     * @param number
     * @return
     */
    public static String formatInteger(int number) {
        String num = number + "";
        if (num.length() < 5) {
            return "";
        }
        if (num.length() > 4 && num.length() < 8) {
            return "万";
        }
        if (num.length() > 8) {
            return "亿";
        }
        return "";
    }

    /***
     * 对数字进行分隔
     * @param number 数值
     * @param format 格式化 例如:###,##0.00保留2位小数
     * @return
     */
    public static String formatInteger(Long number, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(number);
    }

    /**
     * 验证邮箱格式是否正确  正则
     *
     * @param email
     * @return
     */
    public static Boolean formatEmail(String email) {
        Pattern emailPattern = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        if ((email != null) && (email.length() > 5)) {
            Matcher m = emailPattern.matcher(email);
            return m.matches();
        }
        return false;

    }

    /**
     * <b>Summary: 忽略大小写比较两个字符串</b>
     * ignoreCaseEquals()
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean ignoreCaseEquals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    /**
     * <b>Summary: double类型保留指定位数小数，返回字符串,五舍六入</b>
     * formatDoubleToMoney()
     *
     * @param value  传入的参数
     * @param digits 指定位数, 如果为空或者小于0返回原值
     * @param remove 是否去除0，true 去除，false 不去除
     * @return
     */
    public static String formatDoubleToString(Double value, Integer digits, boolean remove) {
        if (value == null) {
            return "";
        }
        if (digits == null || digits < 0) {
            return String.valueOf(value);
        } else if (digits == 0) {
            DecimalFormat df = new DecimalFormat("0");
            return df.format(value);
        } else {
            String temp = "0";
            if (remove) {
                temp = "#";
            }
            StringBuffer buffer = new StringBuffer("0.");
            for (int i = 0; i < digits; i++) {
                buffer.append(temp);
            }
            DecimalFormat df = new DecimalFormat(buffer.toString());
            return df.format(value);
        }
    }

    /**
     * <b>Summary: double类型保留指定位数小数，返回double,五舍六入</b>
     * formatDoubleToMoney()
     *
     * @param value  传入的参数
     * @param digits 指定位数, 如果为空或者小于0返回原值
     * @return
     */
    public static Double formatDoubleToDouble(Double value, Integer digits) {
        if (value == null) {
            return 0.00;
        }
        if (digits == null || digits < 0) {
            return Double.valueOf(String.valueOf(value));
        } else if (digits == 0) {
            DecimalFormat df = new DecimalFormat("0");
            return Double.valueOf(df.format(value));
        } else {
            BigDecimal b = new BigDecimal(value);
            double f1 = b.setScale(digits, BigDecimal.ROUND_HALF_UP).doubleValue();
            return f1;
        }
    }

    /**
     * 时间格式化函数，默认为yyyy-MM-dd HH:mm:ss
     * <b>Summary: </b>
     * formatDate()
     *
     * @param date
     * @param formate
     * @return
     */
    public static String formatDate(Date date, String formate) {
        SimpleDateFormat sdf = null;
        if (date == null) {
            return "";
        }
        if (formate == null || "".equals(formate.trim())) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            sdf = new SimpleDateFormat(formate);
        }
        String result = sdf.format(date);
        return result;
    }

    /**
     * 时间格式化函数，格式化为yyyy-MM-dd HH:mm:ss
     * <b>Summary: </b>
     * formatDate()
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, null);
    }

    /**
     * <b>Summary:截取字符串为指定长度,如果字符串长度没有那么长，返回原字符串  </b>
     * subStringToLength()
     *
     * @param str    原字符串
     * @param length 要截取的长度
     * @param fill   当长度超过以后的补位
     * @return
     */
    public static String subStringToLength(String str, Integer length, String fill) {
        if (str == null) {
            return "";
        }
        if (length == null || length >= str.length()) {//如果长度为null或者大于要截取的字符串的长度放回原来的字符串
            return str;
        }
        if (fill == null) {//如果为null，默认补"..."
            return str.substring(0, length - 3) + "...";
        }
        return str.substring(0, length - fill.length()) + fill;
    }

    /**
     * <b>Summary:截取字符串为指定长度,如果字符串长度没有那么长，返回原字符串,默认补...  </b>
     * subStringToLength()
     *
     * @param str    原字符串
     * @param length 要截取的长度
     * @return
     */
    public static String subStringToLength(String str, Integer length) {
        return subStringToLength(str, length, "...");
    }

    /**
     * <b>Summary:截取字符串为20,如果字符串长度没有那么长，返回原字符串,默认补...  </b>
     * subStringToLength()
     *
     * @param str 原字符串
     * @return
     */
    public static String subStringToLength(String str) {
        return subStringToLength(str, 20, "...");
    }

    /**
     * 验证手机号码
     */
    public static String checkIsPhone(String obj) throws Exception {
        Pattern pattern = Pattern.compile("^1[34578]\\d{9}$");
        Matcher matcher = pattern.matcher(obj);
        if (matcher.matches()) {
            return obj;
        } else {
            throw new Exception("验证手机号码错误");
        }
    }

    /**
     * 传入邮箱域名全部变为小写，然后拼接前缀返回
     * <b>Summary: </b>
     * emailDomainTransform()
     *
     * @param domain
     * @return
     * @throws Exception
     */
    public static String emailDomainTransform(String email) throws Exception {
        if (email == null || "".equals(email.trim()) || email.indexOf("@") == -1) {
            throw new Exception("验证邮箱地址错误");
        }
        String check = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$";
        // /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        boolean flag = matcher.matches();
        if (flag) {
            StringBuffer temp = new StringBuffer();
            temp.append(email.trim().substring(0, email.trim().indexOf("@")));
            temp.append((email.trim().indexOf("@") == email.trim().length() ? "" : email.trim().substring(email.trim().indexOf("@"))).toLowerCase());
            return temp.toString();
        } else {
            throw new Exception("验证邮箱地址错误");
        }
    }

    /**
     * 密码移除空白字符，如果为null则改为“”
     * <b>Summary: </b>
     * passwordRemoveTrim()
     *
     * @param pwd
     * @return
     * @throws Exception
     */
    public static String passwordRemoveTrim(String pwd) {
        if (pwd == null || "".equals(pwd.trim())) {
            return "";
        }
        return pwd.replaceAll("\\s+", "");//替换所有空白字符
    }


    /**
     * 产生随机数字字符串
     *
     * @param min
     * @param max
     * @return
     */
    public static String randomDigit(int min, int max) {
        Random rand = new Random();
        int tmp = Math.abs(rand.nextInt());
        tmp = tmp % (max - min + 1) + min;
        return tmp + "";
    }

    /**
     * @param length
     * @return
     */
    public static String genVerifyCodeByStr(int length) {
        String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);// 0~61
            sf.append(str.charAt(number));
        }
        return sf.toString();
    }

    /**
     * @param length
     * @return
     */
    public static String genVerifyCode(int length) {
        Random random = new Random();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sf.append(String.valueOf((char) result));
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sf.append(String.valueOf((char) result));
                    break;
                case 2:
                    sf.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sf.toString();
    }

    /**
     * 对参数按照逗号分隔成数组，对数组排序后转成string返回
     *
     * @param str
     * @return String
     * @Description
     * @author peng.wang
     * @date 2015-7-21 下午2:12:11
     */
    public static String sort(String str) {
        if (isNotBlank(str)) {
            return str;
        }
        String[] string = str.split(",");
        String rs = null;
        Arrays.sort(string);
        for (int i = 0; i < string.length; i++) {

            if (i == 0) {
                rs = string[i];
            } else {
                rs = rs + "," + string[i];
            }
        }
        return rs;
    }

    /**
     * 判断是否为数字
     *
     * @param str
     * @return String
     * @Description
     * @author wenxiong.zhou
     * @date 2016-1-27
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57)
                return false;
        }
        return true;
    }

    /**
     * 两组字符串用逗号分隔，判断分割后两组数组是否有交集。
     *
     * @param str1
     * @param str2
     * @return boolean
     * @Description
     * @author peng.wang
     * @date 2015-7-22 上午10:17:19
     */
/*	public static boolean isIntersection(String str1,String str2){
		if(StringUtils.isBlank(str1)||StringUtils.isBlank(str2)){
			return false;
		}
		String[] array1 = str1.split(",");
		String[] array2 = str2.split(",");
		for(int i = 0;i<array1.length;i++){
			for(int j = 0;j<array2.length;j++){
				if(array1[i].equalsIgnoreCase(array2[j])){
					return true;
				}
			}
		}
		return false;
	} */
    public static void main(String[] args) throws Exception {
        //System.out.println(emailDomainTransform(""));
        //System.out.println(emailDomainTransform(null));
        //System.out.println(emailDomainTransform("vision_foxmen@163.com"));
		  /*String s = "1     3	2          4      we dads       daj";
		  System.out.println(s);
		  s = s.replaceAll("\\s+", "");
		  String [] sa = s.split("/t");
		  System.out.println(passwordRemoveTrim(s));*/
		/*String str = "1234567890 1234567890 1234567890";

		System.out.println(subStringToLength(str));
		
		System.out.println(subStringToLength(str,5));
		
		System.out.println(subStringToLength(str,5,"$$$"));
		
		System.out.println(subStringToLength(str,15,"####"));*/
        System.out.println(randomDigit(100000, 999999));

    }


    private static final boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * @param strName
     * @return boolean
     * @Description 判断是否有汉字
     * @author shidong.zhao
     * @date 2016-8-10 上午11:32:16
     */
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将str中的emoji表情转为byte数组
     *
     * @param str
     * @return
     */
    public static String resolveToByteFromEmoji(String str) {
        Pattern pattern = Pattern
                .compile("[^(\u2E80-\u9FFF\\w\\s`~!@#\\$%\\^&\\*\\(\\)_+-？（）——=\\[\\]{}\\|;。，、《》”：；“！……’:'\"<,>\\.?/\\\\*)]");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb2 = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb2, resolveToByte(matcher.group(0)));
        }
        matcher.appendTail(sb2);
        return sb2.toString();
    }

    /**
     * 将str中的byte数组类型的emoji表情转为正常显示的emoji表情
     *
     * @param str
     * @return
     */
    public static String resolveToEmojiFromByte(String str) {
        Pattern pattern2 = Pattern.compile("<:([[-]\\d*[,]]+):>");
        Matcher matcher2 = pattern2.matcher(str);
        StringBuffer sb3 = new StringBuffer();
        while (matcher2.find()) {
            matcher2.appendReplacement(sb3, resolveToEmoji(matcher2.group(0)));
        }
        matcher2.appendTail(sb3);
        return sb3.toString();
    }

    private static String resolveToByte(String str) {
        byte[] b = str.getBytes();
        StringBuffer sb = new StringBuffer();
        sb.append("<:");
        for (int i = 0; i < b.length; i++) {
            if (i < b.length - 1) {
                sb.append(Byte.valueOf(b[i]).toString() + ",");
            } else {
                sb.append(Byte.valueOf(b[i]).toString());
            }
        }
        sb.append(":>");
        return sb.toString();
    }

    private static String resolveToEmoji(String str) {
        str = str.replaceAll("<:", "").replaceAll(":>", "");
        String[] s = str.split(",");
        byte[] b = new byte[s.length];
        for (int i = 0; i < s.length; i++) {
            b[i] = Byte.valueOf(s[i]);
        }
        return new String(b);
    }

    /**
     * 得到随机数的字符串（没有相同）
     *
     * @param count      得到几个结果，以逗号分隔
     * @param totalCount 随机池的总数
     * @param baseCount  随机池的基数（最小数）
     * @param strArr     已生成的数列表
     * @return String
     * @Description
     * @author shidong.zhao
     * @date 2016-9-25 下午2:10:40
     */
    public static String getRandomString(Integer count, Integer totalCount, Long baseCount, List<String> strArr) {
        // 随机获取编码
        int[] result = new int[count];
        Integer getCount = 0;
        while (getCount < count) {
            int num = (int) (Math.random() * (totalCount)) + 1;
            boolean flag = true;
            for (int j = 0; j < count; j++) {
                String numStr = (num + baseCount) + "";
                if (num == result[j]) {
                    flag = false;
                    break;
                }
                Boolean bExist = false;
                for (String string : strArr) {
                    if (string.indexOf(numStr) >= 0) {
                        bExist = true;
                        break;
                    }
                }
                if (bExist) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[getCount] = num;
                getCount++;
            }
        }
        String resultStr = "";
        for (int resultItem : result) {
            resultStr += (baseCount + resultItem) + ",";
        }
        if (isNotBlank(resultStr)) {
            resultStr = resultStr.substring(0, resultStr.length() - 1);
        }
        return resultStr;
    }

    /**
     * @param param
     * @return String
     * @Description 大写字母前加下划线
     * @author yuanguo.huang
     * @date 2017-3-8 下午5:42:38
     */
    public static String upperCharToUnderLine(String param) {
        Pattern p = Pattern.compile("[A-Z]");
        if (param == null || param.equals("")) {
            return "";
        }
        StringBuilder builder = new StringBuilder(param);
        Matcher mc = p.matcher(param);
        int i = 0;
        while (mc.find()) {
            builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
            i++;
        }

        if ('_' == builder.charAt(0)) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }


    /**
     * @param source
     * @return String
     * @Description 昵称特殊字符改为*
     * @author zyw
     * @date 2018-3-30 下午8:48:15
     */

    public static String filterEmoji(String source) {
        if (source == null) {
            return source;
        }
        Pattern emoji = Pattern
                .compile(
                        "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                        Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher emojiMatcher = emoji.matcher(source);
        if (emojiMatcher.find()) {
            source = emojiMatcher.replaceAll("*");
            return source;
        }
        return source;
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        return cs1 == null ? cs2 == null : cs1.equals(cs2);
    }
}
