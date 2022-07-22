package utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class WebUtils {
    /**
     * 解析string为class
     *
     * @param s
     * @return
     */
    public static Type parseStringToType(String s) {
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
    public static String parseTypeToString(Type type) {
        if (type != null) {
            String ret = null;
            switch ("" + type) {
                case "int":
                    ret = "整型";
                    break;
                case "class java.lang.String":
                    ret = "字符串型";
                    break;
                case "long":
                    ret = "长整型";
                    break;
                case "boolean":
                    ret = "布尔型";
                    break;
                case "short":
                    ret = "短整型";
                    break;
                case "char":
                    ret = "字符型";
                    break;
            }
            return ret;
        } else {
            return null;
        }
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
        try {
            Date date = sdf.parse(dateString);
            return date;
        } catch (ParseException e) {
            System.out.println("日期格式错误");
            e.printStackTrace();
        }
        return null;
    }
}
