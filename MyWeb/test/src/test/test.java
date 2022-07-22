package test;

import com.lizhi.utils.WebUtils;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class test {
    @Test
    public void test8() {
        Type type=String.class;
        Class cla=String.class;
        System.out.println(type==cla);
    }

    @Test
    public void test7() {
        String s1 = "123";
        s1 += "1111" + ";";
        System.out.println(s1);
    }

    @Test
    public void test6() throws IOException, ClassNotFoundException {
        String beanClassName = "com.hhhh.Demo";
        Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
        String filePath = "E:\\Desktop\\TestJar.jar";
        String uFilePath = "file:E:\\Desktop\\TestJar.jar";

        URL url1 = new URL(uFilePath);
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url1}, Thread.currentThread()
                .getContextClassLoader());
        List<JarEntry> jarEntryList = new ArrayList<>();
        JarFile jarFile = new JarFile(filePath);
        Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
        while (jarEntryEnumeration.hasMoreElements()) {
            JarEntry jarEntry = jarEntryEnumeration.nextElement();
            if (//jarEntry.getName().startsWith(filePath) &&
                    jarEntry.getName().endsWith(".class")) {
                jarEntryList.add(jarEntry);
            }
        }

        for (JarEntry entry : jarEntryList) {
            String className = entry.getName().replace('/', '.');
            className = className.substring(0, className.length() - 6);
            if (!classMap.containsKey(beanClassName)) {
                Class<?> loadClass = urlClassLoader.loadClass(className);
                classMap.put(className, loadClass);
            }
        }
        try {
            Method printMethod = classMap.get(beanClassName).getMethod("getInfo");
            printMethod.invoke(classMap.get(beanClassName).newInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseDate() {
        System.out.println(WebUtils.parseDate("2018-08-24"));
    }

    @Test
    public void test5() {
        String[] aaa = {"123123", "sdasdasd"};
        String ss = "";
        ss += aaa[0];
        ss += aaa[1];
        System.out.println(ss);
    }

    @Test
    public void test4() {
        //外部jar所在位置
        String path = "file:E:\\Desktop\\Programmer\\JavaFile\\MyWeb\\test\\web\\WEB-INF\\lib\\TestJar.jar";
        URLClassLoader urlClassLoader = null;
        Class<?> MyTest = null;
        try {
            //通过URLClassLoader加载外部jar
            urlClassLoader = new URLClassLoader(new URL[]{new URL(path)});
            //获取外部jar里面的具体类对象 这个可以通过之前动态装载获得
            MyTest = urlClassLoader.loadClass("com.baidu.Demo");
            //创建对象实例
            Object instance = MyTest.newInstance();
            //获取实例当中的方法名为show，参数只有一个且类型为string的public方法
            Method method = MyTest.getMethod("getInfo");
            //传入实例以及方法参数信息执行这个方法
            Object ada = method.invoke(instance);
            System.out.println(ada);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //卸载关闭外部jar
            try {
                urlClassLoader.close();
            } catch (IOException e) {
                System.out.println("关闭外部jar失败：" + e.getMessage());
            }
        }
    }

    /**
     * 动态装载jar并运行指定方法
     */
    @Test
    public void test2() {
        String path = "E:\\Desktop\\Programmer\\JavaFile\\MyWeb\\test\\web\\WEB-INF\\lib\\";
        String fileName = "TestJar.jar";
        String className = null;
        try {
            JarFile jarFile = new JarFile(path + fileName);

            Enumeration<JarEntry> e = jarFile.entries();

            JarEntry entry;
            while (e.hasMoreElements()) {
                entry = (JarEntry) e.nextElement();
                //
                if (entry.getName().indexOf("META-INF") < 0 && entry.getName().indexOf(".class") >= 0) {
                    String classFullName = entry.getName();
                    //去掉后缀.class
                    className = classFullName.substring(0, classFullName.length() - 6).replace("/", ".");
                    System.out.println(className);

                    Class<?> c = null;
                    try {
                        try {
                            // 用className这个类来装载c
                            c = Class.forName(className);
                            Object instance = c.newInstance();
                            Method method = c.getMethod("getSum", int.class, int.class);
                            System.out.println("运行动态装载jar包的getSum方法，并获得输出");
                            System.out.println(method.invoke(instance, 2, 3));
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        Method[] methods = c.getMethods();
                        for (Method method : methods) {
                            String methodName = method.getName();
                            System.out.println("方法名称:" + methodName);
                            Type returnType = (Type) method.getGenericReturnType();
                            System.out.println("返回值类型" + returnType);
                            Class<?>[] parameterTypes = method.getParameterTypes();
                            System.out.println("参数类型");
                            for (Class<?> clas : parameterTypes) {
//                                System.out.println(clas.getName());
//                                String parameterName = clas.getSimpleName();
//                                System.out.println("参数类型数组："+clas.getTypeParameters());
//                                System.out.println("参数类型:" + parameterName);
                                System.out.println(WebUtils.parseClassToString(clas));
                            }
                            System.out.println("==========================");
                        }
                    } catch (Exception e1) {

                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        System.out.println("E:\\Desktop\\Programmer\\JavaFile\\MyWeb\\modelManagementSystem\\web\\file\\attachment".length());
    }


}
