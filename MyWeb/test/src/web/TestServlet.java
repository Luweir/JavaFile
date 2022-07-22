package web;

//import com.jspsmart.upload.File;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pojo.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TestServlet extends BaseServlet {
    protected void test4(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String modelName = req.getParameter("modelName");
        System.out.println(modelName);
    }

    protected void test3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SmartUploadException {
        req.setCharacterEncoding("gbk");
        System.out.println("进入test3");
        SmartUpload su = new SmartUpload();
        su.initialize(this.getServletConfig(), req, resp);
        su.upload();
        System.out.println("完成upload");
        String[] tags = su.getRequest().getParameterValues("modelTags");
        for (int i = 0; i < tags.length; i++) {
            System.out.println(tags[i]);
        }


    }

    protected void test(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入servlet");
        req.setAttribute("count", 2);
        // 重定向回之前页面
        req.getRequestDispatcher("/pages/test1.jsp").forward(req, resp);
    }
//        Map<Method, List<Parameter>> methodParameters = new HashMap<>();
//        List<Parameter> parameterList = new ArrayList<>();
//        parameterList.add(new Parameter(1, "参数1", null, null, "字符串型", "这是参数1描述", null, null, "这是参数1示例"));
//        parameterList.add(new Parameter(2, "参数2", null, null, "字符串型", "这是参数2描述", null, null, "这是参数2示例"));
//        parameterList.add(new Parameter(3, "参数3", null, null, "字符串型", "这是参数3描述", null, null, "这是参数3示例"));
//        methodParameters.put(new Method(1, "方法1", "callname1", 1, "字符串型", "这是方法1", 3, "这是方法1的预期返回值"), parameterList);
////        List<Parameter> parameterList2 = new ArrayList<>();
//        // 不能parameterList.clear(),因为只清除了map中的数据，其内部指针指向的地址未改变,会导致clear前后内容一样
//        parameterList = new ArrayList<>();
//        parameterList.add(new Parameter(4, "参数4", null, null, "字符串型", "这是参数4描述", null, null, "这是参数4示例"));
//        parameterList.add(new Parameter(5, "参数5", null, null, "字符串型", "这是参数5描述", null, null, "这是参数5示例"));
//        methodParameters.put(new Method(2, "方法2", "callname2", 1, "整型", "这是方法2", 2, "这是方法2的预期返回值"), parameterList);
//        req.setAttribute("methods", methodParameters);
//        req.getRequestDispatcher("/pages/test2.jsp").forward(req, resp);
//    }

    //    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
//        req.setCharacterEncoding("gbk");
//        System.out.println("doPost");
//        SmartUpload su = new SmartUpload();
//        try {
//            su.initialize(this.getServletConfig(), req, resp);
//            su.upload();
//            System.out.println(su.getRequest().getParameter("attachmentDescription"));
//            File file = su.getFiles().getFile(0);
//            System.out.println(file.getFileName());
//            file.saveAs("E:\\Desktop\\Programmer\\JavaFile\\MyWeb\\test\\web\\upload\\" + file.getFileName());
//            file = su.getFiles().getFile(1);
//            file.saveAs("E:\\Desktop\\Programmer\\JavaFile\\MyWeb\\test\\web\\upload\\" + file.getFileName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    //    }
    /// 上传文件
    /// </summary>
    /// <param name="file">通过form表达提交的文件</param>
    /// <param name="virpath">文件要保存的虚拟路径</param>
    protected void parseJarFile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String className = null;
        String fileName = null;
        // 一、将文件存到磁盘
        try {
            // 1.创建磁盘文件项工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 2.创建核心解析器对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            // 3.解析request请求，返回list集合
            List<FileItem> list = fileUpload.parseRequest(req);
            // 4.遍历list，获取上传项
            for (FileItem fileItem : list) {
                // 5.判断是文件项还是普通项
                if (fileItem.isFormField()) {
                    // a.获取name属性
                    String name = fileItem.getFieldName();
                    // b.获取value属性
                    String value = fileItem.getString();
                    System.out.println("普通文本项：" + name + "=" + value);
                } else {
                    // 上传文件项
                    // a.获取文件名
                    fileName = fileItem.getName();
                    // 获取上传文件扩展名
                    String extName = FileUtil.extName(fileName);
                    // 生成随机文件名
//                    filename = IdUtil.simpleUUID() + "." + extName;
                    // b.获取文件字节输入流
                    InputStream in = fileItem.getInputStream();
                    System.out.println("上传文件名：" + fileName + "，上传文件流：" + in);
                    // c.获取服务器upload目录真实路径...
//                    String realPath = req.getServletContext().getRealPath("/upload/");
                    String realPath = "E:\\Desktop\\Programmer\\JavaFile\\MyWeb\\test\\web\\upload\\";
                    // d.设置文件字节输出流
                    FileOutputStream out = new FileOutputStream(new File(realPath + fileName));
                    // 流的copy
                    IoUtil.copy(in, out);
                    // 关流
                    out.close();
                    in.close();

                    /*二、加载jar包 返回类名和方法名，有参数信息更好*/
                    Map<pojo.Method, List<String>> mapMethod = new HashMap<>();
                    // 解析jar 文件
                    JarFile jarFile = new JarFile(realPath + fileName);
                    Enumeration<JarEntry> e = jarFile.entries();
                    JarEntry entry;
                    while (e.hasMoreElements()) {
                        entry = (JarEntry) e.nextElement();
                        //
                        if (entry.getName().indexOf("META-INF") < 0 && entry.getName().indexOf(".class") >= 0) {
                            String classFullName = entry.getName();
                            //去掉后缀.class
                            className = classFullName.substring(0, classFullName.length() - 6).replace("/", ".");
                            System.out.println("className:" + className);
                            Class<?> c = null;
                            try {
                                try {
                                    // 用className这个类来装载c
                                    c = Class.forName(className);
//                                    Object instance = c.newInstance();
//                                    Method method = c.getMethod("getSum", int.class, int.class);
//                                    System.out.println("运行动态装载jar包的getSum方法，并获得输出");
//                                    System.out.println(method.invoke(instance, 2, 3));
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                                Method[] methods = c.getMethods();
                                for (Method method : methods) {
                                    String methodName = method.getName();
                                    pojo.Method curMethod = new pojo.Method();
                                    List<String> paramTypes = new ArrayList<>();
                                    // 设置方法名
                                    // 设置方法返回值类型
                                    // 设置方法参数个数
                                    curMethod.setMethodName(methodName);
                                    curMethod.setReturnType("" + method.getReturnType());
                                    curMethod.setParameterCount(method.getParameterCount());

                                    System.out.println("方法名称:" + methodName);

                                    System.out.println("返回值类型" + method.getReturnType());

                                    Class<?>[] parameterTypes = method.getParameterTypes();
                                    for (Class<?> clas : parameterTypes) {
                                        String paramType = clas.getSimpleName();
                                        // 将参数类型加入该方法的参数类型数组中
                                        paramTypes.add(paramType);
                                        System.out.println("参数类型:" + paramType);
                                    }
                                    mapMethod.put(curMethod, paramTypes);
                                    System.out.println("==========================");
                                }
                                break;
                            } catch (Exception e1) {
                            }
                        }
                    }
                    // 三、设置域对象
                    req.setAttribute("mapMethod", mapMethod);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

