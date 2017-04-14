package com.aadhk.customer.util;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtil {

    public static String getNoEmpty(String... strs) {
        for (String item : strs) {
            if (!TextUtils.isEmpty(item)) return item;
        }
        return "";
    }


    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188、178
	    联通：130、131、132、152、155、156、185、186、176
	    电信：133、153、180、189、（1349卫通）、177
	    总结起来就是第一位必定为1，第二位必定为3或5或8或7，其他位置的可以为0-9
		 */
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[3458]"代表第二位可以为3、4、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return !m.matches();
    }

    /**
     * ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$
     * ^                 # start-of-string                                字母开头
     * (?=.*[0-16])       # a digit must occur at least once              包含一个数字
     * (?=.*[a-z])       # a lower case letter must occur at least once   至少包含一个小写字母
     * (?=.*[A-Z])       # an upper case letter must occur at least once  至少包含一个大写字母
     * (?=.*[@#$%^&+=])  # a special character must occur at least once   至少包含一个特殊字符
     * (?=\S+$)          # no whitespace allowed in the entire string     没有空格
     * .{8,}             # anything, at least eight places though         至少8个字符
     * $                 # end-of-string                                  以字母结尾
     * <p>
     * 这里的改进为必须要
     * 1. 字母开头
     * 2. 必须要有数字
     * 3. 必须要有字母
     * 4. 可以使用@#$%^&+=特殊符号
     * 5. 位数为6-16位
     */
    public static boolean isPassword(String pwd) {
        String str = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,16}";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(pwd);
        return m.matches();
    }

    public static boolean isEmpty(String str) {
        if (str == null) return true;
        return TextUtils.isEmpty(str.trim());
    }

    public static boolean isEqual(String str1, String str2) {
        if (str1 == null || str2 == null) return false;
        return str1.equals(str2);
    }

    /**
     * 判断是否全是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 判断是否是银行卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId
                .substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null
                || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * 验证日期字符串是否是YYYY-MM-DD格式
     *
     * @param str
     * @return
     */
    private static boolean isDataFormat(String str) {
        boolean flag = false;
        // String
        // regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
        String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern1 = Pattern.compile(regxStr);
        Matcher isNo = pattern1.matcher(str);
        if (isNo.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 空值null返回"",防止脏数据奔溃
     */
    public static String checkValue(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }


    public static String displayAmount(int currencyPosition, int decimalPlace, double value, String currSign) {
        String pattern = "#,###,##0";
        for (int i = 1; i <= decimalPlace; i++) {
            if (i == 1) {
                pattern += ".0";
            } else {
                pattern += "0";
            }
        }

        if (currencyPosition == 0) {
            pattern = "'" + currSign.replace("'", "''") + "'" + pattern;
        } else if (currencyPosition == 1) {
            pattern = pattern + "'" + currSign.replace("'", "''") + "'";
        } else if (currencyPosition == 2) {
            pattern = "'" + currSign.replace("'", "''") + "' " + pattern;
        } else if (currencyPosition == 3) {
            pattern = pattern + " '" + currSign.replace("'", "''") + "'";
        }
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(value);
    }

    public static String displayAmount(int decimalPlace, double value) {
        String pattern = "";
        if (decimalPlace == 0) {
            pattern += "#,###,##0";
        } else if (decimalPlace == 1) {
            pattern += "#,###,##0.0";
        } else if (decimalPlace == 2) {
            pattern += "#,###,##0.00";
        } else if (decimalPlace == 3) {
            pattern += "#,###,##0.000";
        }
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(value);
    }

    public static String displayPrice(double value) {
        DecimalFormat df = new DecimalFormat("#,###,##0.#");
        df.setMaximumFractionDigits(2);
        return df.format(value);
    }

    public static String displayQty(double value) {
        DecimalFormat df = new DecimalFormat("##0.#");
        return df.format(value);
    }

    public static String displayQty(double value, int maxFractionDigit) {
        DecimalFormat df = new DecimalFormat("##0.#");
        df.setMaximumFractionDigits(maxFractionDigit);
        return df.format(value);
    }
}

