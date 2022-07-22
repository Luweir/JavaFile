package com.lizhi.utils;

import com.alibaba.druid.sql.visitor.functions.Char;
import org.apache.commons.beanutils.BeanUtils;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.text.SimpleDateFormat;

public class WebUtils {

    public static Object parseStringToValue(String s, Class type) {
        if (int.class.equals(type)) {
            return Integer.parseInt(s);
        } else if (String.class.equals(type)) {
            return s;
        } else if (long.class.equals(type)) {
            return Long.parseLong(s);
        } else if (boolean.class.equals(type)) {
            return Boolean.parseBoolean(s);
        } else if (float.class.equals(type)) {
            return Float.parseFloat(s);
        } else if (double.class.equals(type)) {
            return Double.parseDouble(s);
        } else if (short.class.equals(type)) {
            return Short.parseShort(s);
        } else if (char.class.equals(type)) {
            return s.charAt(0);
        }
        return null;
    }

    /**
     * 解析string为class
     *
     * @param s
     * @return
     */
    public static Class parseStringToClass(String s) {
        if (s != null) {
            switch (s) {
                case "整型":
                    return int.class;
                case "字符串型":
                    return String.class;
                case "长整型":
                    return long.class;
                case "布尔型":
                    return boolean.class;
                case "短整型":
                    return short.class;
                case "字符型":
                    return char.class;
                case "单精度型":
                    return float.class;
                case "双精度型":
                    return double.class;
            }
        }
        return null;
    }

    /**
     * 解析Class为String返回
     *
     * @param type
     * @return
     */
    public static String parseClassToString(Type type) {
        if (type != null) {
            switch ("" + type) {
                case "int":
                    return "整型";
                case "class java.lang.String":
                    return "字符串型";
                case "long":
                    return "长整型";
                case "boolean":
                    return "布尔型";
                case "short":
                    return "短整型";
                case "char":
                    return "字符型";
                case "float":
                    return "单精度型";
                case "double":
                    return "双精度型";
            }
        }
        return null;
    }

    /**
     * 把 Map 中的值 注入到对应的 JavaBean 属性中，降低耦合度，使其应用广泛
     *
     * @param value Map类型更加广泛，更加优雅， 相对 req而言
     * @param bean
     */
    // 使用泛型
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            System.out.println("注入之前：" + bean);
            /**
             * 把所有参数都注入 bean 对象中
             */
            BeanUtils.populate(bean, value);
            System.out.println("注入之后：" + bean);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static float parseFloat(String floatStr, float defaultValue) {
        if (floatStr != null) {
            try {
                return Float.parseFloat(floatStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    /**
     * 将字符串 转换为 对应的 int 类型
     *
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue) {
        if (strInt != null) {
            try {
                return Integer.parseInt(strInt);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public static Date parseDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (dateString != null && dateString != "") {
            try {
                Date date = sdf.parse(dateString);
                return date;
            } catch (ParseException e) {
                System.out.println("日期格式错误");
                e.printStackTrace();
            }
        }
        return null;
    }
}
