package com.lizhi.web;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.google.gson.Gson;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.lizhi.dao.DirectoryDao;
import com.lizhi.dao.ParameterDao;
import com.lizhi.dao.impl.DirectoryDaoImpl;
import com.lizhi.dao.impl.ParameterDaoImpl;
import com.lizhi.pojo.*;
import com.lizhi.pojo.Record;
import com.lizhi.service.*;
import com.lizhi.service.impl.*;
import com.lizhi.utils.WebUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class ModelServlet extends BaseServlet {
    final private ModelService modelService = new ModelServiceImpl();
    final private MethodService methodService = new MethodServiceImpl();
    final private RecordService recordService = new RecordServiceImpl();
    final private ParameterDao parameterDao = new ParameterDaoImpl();
    final private DownloadService downloadService = new DownloadServiceImpl();
    final private UserService userService = new UserServiceImpl();


    /**
     * 模型查询分页处理
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageForModelQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Model> modelPage = modelService.page(pageNo, pageSize);
        modelPage.setUrl("modelServlet?action=pageForModelQuery");
        req.setAttribute("page", modelPage);
        req.getRequestDispatcher("/pages/model/model_query.jsp").forward(req, resp);
    }


    /**
     * 模型可修改页面
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateModel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        int modelId = WebUtils.parseInt(req.getParameter("modelId"), 0);
        if (modelId != 0) {
            if (type.equals("enter")) {
                Model model = modelService.queryModelByModelId(modelId);
                req.setAttribute("model", model);
                DirectoryDao directoryDao = new DirectoryDaoImpl();
                List<Directory> directoryList = directoryDao.queryAllDirectory();
                directoryList.add(new Directory("未分类", null));
                req.getSession().setAttribute("directories", directoryList);
                req.getRequestDispatcher("/pages/manage/model_update.jsp").forward(req, resp);
                return;
            }
            if (type.equals("save")) {
                Model model = modelService.queryModelByModelId(modelId);
                model.setModelName(req.getParameter("modelName"));
                model.setModelType(req.getParameter("modelType"));
                model.setDevelopmentLanguage(req.getParameter("developmentLanguage"));
                model.setVersion(req.getParameter("version"));
                model.setModelDescription(req.getParameter("modelDescription"));
                model.setApplicationField(req.getParameter("applicationField"));
                model.setModelTags(req.getParameter("modelTags"));
                model.setCreateDepartment(req.getParameter("createDepartment"));
                model.setCompletionDepartment(req.getParameter("completionDepartment"));
                model.setModelDirectoryCode(req.getParameter("modelDirectoryCode"));
                model.setRuntimeEnvironment(req.getParameter("runtimeEnvironment"));
                model.setTestDescription(req.getParameter("testDescription"));
                modelService.updateModel(model);
                modelManagement(req, resp);
            }
        }
    }

    /**
     * 更新目录信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateDirectory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DirectoryDao directoryDao = new DirectoryDaoImpl();
        String type = req.getParameter("type");
        if (type.equals("rename")) {
            String oldName = req.getParameter("oldName");
            String newName = req.getParameter("newName");
            // 先把该分类下的模型 directoryCode修改
            modelService.updateModelDirectoryCode(oldName, newName);
            // 修改directory表中的信息
            directoryDao.updateDirectoryNameByName(oldName, newName);
            resp.getWriter().write("true");
            return;
        }
        if (type.equals("drop")) {
            String directoryName = req.getParameter("directoryName");
            // 先把这个分类下的所有模型的directory置为未分类
            modelService.updateModelDirectoryCode(directoryName, "未分类");
            List<Directory> directoryList = directoryDao.queryAllDirectory();
            for (int i = 0; i < directoryList.size(); i++) {
                if (directoryList.get(i).getParentDirectory().equals(directoryName)) {
                    modelService.updateModelDirectoryCode(directoryList.get(i).getCurDirectory(), "未分类");
                }
            }
            // 删除父目录或子目录是directoryName的目录
            directoryDao.dropDirectory(directoryName);
            resp.getWriter().write("true");
            return;
        }
        if (type.equals("add")) {
            String directoryName = req.getParameter("directoryName");
            String parentName = req.getParameter("parentName");
            directoryDao.addDirectory(new Directory(directoryName, parentName));
            resp.getWriter().write("true");
            return;
        }

    }

    /**
     * 分类管理
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void classificationManagement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DirectoryDao directoryDao = new DirectoryDaoImpl();
        // 接受的参数：分类名，级数
        String directoryName = req.getParameter("directoryName");
        String layer = req.getParameter("layer");
        // 根据分类名查找该分类下面的二级分类
        Set<Directory> directories = new LinkedHashSet<>();
        List<Directory> directoriesList = directoryDao.queryAllDirectory();
        for (int i = 0; i < directoriesList.size(); i++) {
            if (directoriesList.get(i).getParentDirectory().equals(directoryName)) {
                Directory directory = directoriesList.get(i);
                directory.setHaveChild("false");
                for (int j = 0; j < directoriesList.size(); j++) {
                    // 如果有孩子
                    if (directoriesList.get(j).getParentDirectory().equals(directory.getCurDirectory())) {
                        directory.setHaveChild("true");
                        break;
                    }
                }
                directories.add(directory);
            }
        }
        Gson gson = new Gson();
        resp.getWriter().write(gson.toJson(directories));


    }

    /**
     * 检查是否存在模型名
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void existModelName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = modelService.queryModelByName(req.getParameter("modelName"));
        if (model != null) {
            resp.getWriter().write("true");
        }
    }

    /**
     * 返回现有模型的directory
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void modelDirectory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DirectoryDao directoryDao = new DirectoryDaoImpl();
        List<Directory> directories = directoryDao.queryAllDirectory();
        Map<String, List<String>> calculationModelDirectory = new LinkedHashMap<>();
        Map<String, List<String>> functionModelDirectory = new LinkedHashMap<>();
        for (int i = 0; i < directories.size(); i++) {
            // 找二级分类
            Directory directory = directories.get(i);
            if (directory.getParentDirectory().equals("计算模型")) {
                List<String> calSubDirectory = new ArrayList<>();
                // 找三级分类
                for (int j = 0; j < directories.size(); j++) {
                    if (directories.get(j).getParentDirectory().equals(directory.getCurDirectory())) {
                        calSubDirectory.add(directories.get(j).getCurDirectory());
                    }
                }
                calculationModelDirectory.put(directory.getCurDirectory(), calSubDirectory);
            } else if (directory.getParentDirectory().equals("功能模型")) {
                // 如果是功能模型
                List<String> funcSubDirectory = new ArrayList<>();
                // 找三级分类
                for (int j = 0; j < directories.size(); j++) {
                    if (directories.get(j).getParentDirectory().equals(directory.getCurDirectory())) {
                        funcSubDirectory.add(directories.get(j).getCurDirectory());
                    }
                }
                functionModelDirectory.put(directory.getCurDirectory(), funcSubDirectory);
            }
        }
        // 如果是通过ajax访问的
        if (req.getParameter("type") != null && req.getParameter("type").equals("ajax")) {
            Gson gson = new Gson();
            if (req.getParameter("modelType").equals("计算模型")) {
                resp.getWriter().write(gson.toJson(calculationModelDirectory));
            } else {
                resp.getWriter().write(gson.toJson(functionModelDirectory));
            }
            return;
        }
        req.setAttribute("calculationModelDirectory", calculationModelDirectory);
        req.setAttribute("functionModelDirectory", functionModelDirectory);
        // model下的模型分类页面
        if (req.getParameter("type") != null && req.getParameter("type").equals("notAdmin")) {
            req.getRequestDispatcher("/pages/model/model_classification.jsp").forward(req, resp);
            return;
        }
        // manage 下的分类管理页面
        if (req.getParameter("type") != null && req.getParameter("type").equals("admin")) {
            req.getRequestDispatcher("/pages/manage/classification_management.jsp").forward(req, resp);
            return;
        }
    }

    /**
     * 模型分类，根据modelDirectoryCode查找模型并封装成JSON字符串返回
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void modelClassify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String modelDirectoryCode = req.getParameter("modelDirectoryCode");
        if (modelDirectoryCode != null) {
            System.out.println(modelDirectoryCode);
            // 1 根据类型查找模型
            List<Model> models = modelService.getModelsByDirectoryCode(modelDirectoryCode);
            // 2 打包成json格式返回
            Gson gson = new Gson();
            resp.getWriter().write(gson.toJson(models));
        } else {
            System.out.println("modelType 为 null");
        }
    }

    /**
     * 筛选模型
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void screeningModel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、先获得所有模型;
        List<Model> modelList = modelService.getAllModels();
        // 要返回的模型
        List<Model> models = new ArrayList<>();
//        2、根据筛选条件删除模型；
        String modelName = req.getParameter("modelName") == "" ? null : req.getParameter("modelName");
        String modelType = req.getParameter("modelType") == "" ? null : req.getParameter("modelType");
        String modelDirectoryCode = req.getParameter("modelDirectoryCode") == "" ? null : req.getParameter("modelDirectoryCode");
        String keyWord = req.getParameter("keyWord") == "" ? null : req.getParameter("keyWord");
        String modelState = req.getParameter("modelState") == "" ? null : req.getParameter("modelState");
        Date startReleaseDate = WebUtils.parseDate(req.getParameter("startReleaseDate"));
        Date endReleaseDime = WebUtils.parseDate(req.getParameter("endReleaseDime"));

        String back = req.getParameter("back");
        System.out.println(back);
        modelList.forEach(item -> {
            if ((modelName == null || modelName.equals(item.getModelName())) && (modelType == null || modelType.equals(item.getModelType())) && (modelState == null || modelState.equals(item.getModelState()))) {
                if (keyWord == null || item.getModelDescription().contains(keyWord) || item.getModelName().contains(keyWord)) {
                    if (modelDirectoryCode == null || item.getModelDirectoryCode().equals(modelDirectoryCode)) {
                        if (startReleaseDate == null || (item.getModelState().equals("已发布") && startReleaseDate.getTime() < item.getReleaseDate().getTime())) {
                            if (endReleaseDime == null || endReleaseDime.getTime() > item.getReleaseDate().getTime()) {
                                models.add(item);
                            }
                        }
                    }
                }

            }
        });

        for (int i = 0; i < models.size(); i++) {
            User user = userService.getUserByUserId(models.get(i).getRegisterUserId());
            models.get(i).setRegisterName(user.getUsername());
        }
        req.setAttribute("models", models);
//        3、返回模型到指定的页面；
        if ("model_management".equals(back)) {
            req.getRequestDispatcher("/pages/manage/" + back + ".jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/pages/model/" + back + ".jsp").forward(req, resp);
        }

    }


    /**
     * 模型推荐：按照已发布的用户未申请的模型下载量、评分取前五个，下载量优先
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void modelRecommend(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Model> models = modelService.getRecommendModels(user.getId());
        req.setAttribute("models", models);
        req.getRequestDispatcher("/pages/model/model_recommend.jsp").forward(req, resp);
    }

    /**
     * 删除模型
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteModel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int modelId = WebUtils.parseInt(req.getParameter("modelId"), 0);
        if (modelId != 0) {
            // deleteModel(int modelId) 删除跟id=modelId的模型有关的任何条项
            modelService.deleteModel(modelId);
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }


    }

    /**
     * 返回所有已发布、未发布、拒绝发布的模型到模型发布页面
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void modelRelease(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Model> modelList = modelService.queryAllNormalModel();
        List<Model> models = new ArrayList<>();

        for (int i = 0; i < modelList.size(); i++) {
            User user = userService.getUserByUserId(modelList.get(i).getRegisterUserId());
            modelList.get(i).setRegisterName(user.getUsername());
        }
        String modelName = req.getParameter("modelName") == "" ? null : req.getParameter("modelName");
        String modelType = req.getParameter("modelType") == "" ? null : req.getParameter("modelType");
        String modelState = req.getParameter("modelState") == "" ? null : req.getParameter("modelState");
        Date startRegisterDate = WebUtils.parseDate(req.getParameter("startRegisterDate"));
        Date endRegisterDime = WebUtils.parseDate(req.getParameter("endRegisterDime"));

        modelList.forEach(item -> {
            if ((modelName == null || modelName.equals(item.getModelName())) && (modelType == null || modelType.equals(item.getModelType())) && (modelState == null || modelState.equals(item.getModelState()))) {
                if (startRegisterDate == null || startRegisterDate.getTime() < item.getRegisterDate().getTime()) {
                    if (endRegisterDime == null || endRegisterDime.getTime() > item.getRegisterDate().getTime()) {
                        models.add(item);
                    }
                }
            }
        });

        req.setAttribute("models", models);
        req.getRequestDispatcher("/pages/model/model_release.jsp").forward(req, resp);

    }

    /**
     * 模型弃用
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void modelAbandon(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int modelId = WebUtils.parseInt(req.getParameter("modelId"), 0);
        String operation = req.getParameter("operation");
        Model model = modelService.queryModelByModelId(modelId);
        if (model != null) {
            if ("弃用".equals(operation)) {
                // 如果传入的指令是弃用
                model.setModelState("已弃用");
                modelService.updateModel(model);
                resp.getWriter().write("恢复");
            } else {
                // 如果传入的指令是恢复
                model.setModelState("已发布");
                modelService.updateModel(model);
                resp.getWriter().write("弃用");
            }

        } else {
            resp.getWriter().write("false");
        }
    }

    /**
     * 返回所有模型信息，共管理员操作，包括发布、弃用、删除
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void modelManagement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DirectoryDao directoryDao = new DirectoryDaoImpl();
        System.out.println("进入modelManagement");
        UserService userService = new UserServiceImpl();
        List<Model> models = modelService.getAllModels();
        for (int i = 0; i < models.size(); i++) {
            User user = userService.getUserByUserId(models.get(i).getRegisterUserId());
            models.get(i).setRegisterName(user.getUsername());
        }
        req.setAttribute("models", models);
        List<Directory> directoryList = directoryDao.queryAllDirectory();
        directoryList.add(new Directory("未分类", null));
        req.getSession().setAttribute("directories", directoryList);
        req.getRequestDispatcher("/pages/manage/model_management.jsp").forward(req, resp);
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void downloadModel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("downloadModel");
        int downloadId = WebUtils.parseInt(req.getParameter("downloadId"), 0);
        Download download = downloadService.getDownloadById(downloadId);
        Model model = modelService.queryModelByModelId(download.getModelId());
        SmartUpload su = new SmartUpload();
        su.initialize(getServletConfig(), req, resp);
        try {
            su.downloadFile(model.getModelFilePath());
            // 更新download条项，更新单人下载量
            if (download.getDownloads() == null) {
                download.setDownloads(1);
            } else {
                download.setDownloads(download.getDownloads() + 1);
            }
            downloadService.updateDownload(download);

            // 更新模型整体下载量
            if (model.getDownloads() == null) {
                model.setDownloads(1);
            } else {
                model.setDownloads(model.getDownloads() + 1);
            }
            modelService.updateModel(model);
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试方法运行是否正常
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void testMethod(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 取出json格式的数据
        System.out.println("进入testMethod");
        String filePath = req.getParameter("filePath");
        String methodCallName = req.getParameter("methodCallName");
        String className = req.getParameter("className");
        String[] paramTypes = req.getParameterValues("paramTypes");
        String[] paramSamples = req.getParameterValues("paramSamples");
        Class[] argumentsType = new Class[paramTypes.length];
        Object[] args = new Object[paramTypes.length];
        // 完成各种String 到 Class 的类型转换
        for (int i = 0; i < paramTypes.length; i++) {
            Class type = WebUtils.parseStringToClass(paramTypes[i]);
            argumentsType[i] = type;
            args[i] = WebUtils.parseStringToValue(paramSamples[i], type);
        }
        // 读取文件，实例化对象，调用方法，传递参数
        String uFilePath = "file:" + filePath;
        URL url1 = new URL(uFilePath);
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url1}, Thread.currentThread().getContextClassLoader());
        JarFile jarFile = new JarFile(filePath);
        try {
            Class<?> c = urlClassLoader.loadClass(className);
            try {
                Object instance = c.newInstance();
                // 获得对应方法，执行该方法
                java.lang.reflect.Method method = c.getDeclaredMethod(methodCallName, argumentsType);
                System.out.println("找到了method" + method.getName());
                System.out.println("输出结果" + method.invoke(instance, args));
                // 封装成json数据写回将结果写回
                Gson gson = new Gson();
                Map<String, String> mapDate = new HashMap<>();
                mapDate.put("result", "" + method.invoke(instance, args));
                resp.getWriter().write(gson.toJson(mapDate));
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * 解析jar包并返回类、方法、参数信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void parseJar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入parseJar方法");
        resp.setContentType("test/html;charset=utf-8");
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
//                    System.out.println("servlet中获得项目绝对路径:" + req.getContextPath());
                    String realPath = "E:\\Desktop\\Programmer\\JavaFile\\MyWeb\\modelManagementSystem\\web\\file\\upload\\";
                    // d.设置文件字节输出流
                    FileOutputStream out = new FileOutputStream(realPath + fileName);
                    // 流的copy
                    IoUtil.copy(in, out);
                    // 关流
                    out.close();
                    in.close();

                    /*二、加载jar包 返回类名和方法名，有参数信息更好*/
                    // key:methodName value:method
                    Map<String, Object> mapData = new HashMap<>();
                    Map<String, Method> mapMethod = new HashMap<>();
                    // key:methodName value:paramType
                    Map<String, List<String>> mapParams = new HashMap<>();
                    // 存放类名、类的map
                    Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

                    // 解析jar 文件;
                    String filePath = realPath + fileName;
                    String uFilePath = "file:" + filePath;
                    URL url1 = new URL(uFilePath);
                    URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url1}, Thread.currentThread().getContextClassLoader());

                    JarFile jarFile = new JarFile(filePath);

                    Enumeration<JarEntry> e = jarFile.entries();
                    while (e.hasMoreElements()) {
                        JarEntry entry = e.nextElement();
                        //
                        if (entry.getName().endsWith(".class")) {
                            String classFullName = entry.getName();
                            //去掉后缀.class
                            className = classFullName.substring(0, classFullName.length() - 6).replace("/", ".");
                            System.out.println("className:" + className);
                            // 把类名放进去
                            mapData.put("className", className);

                            Class<?> c = urlClassLoader.loadClass(className);
                            try {
                                java.lang.reflect.Method[] methods = c.getDeclaredMethods();
                                for (java.lang.reflect.Method method : methods) {
                                    String methodName = method.getName();
                                    Method curMethod = new Method();
                                    List<String> paramTypes = new ArrayList<>();
                                    // 设置方法名
                                    // 设置方法返回值类型
                                    // 设置方法参数个数
                                    curMethod.setMethodName(methodName);

                                    curMethod.setReturnType(WebUtils.parseClassToString(method.getReturnType()));
                                    curMethod.setParameterCount(method.getParameterCount());

                                    System.out.println("方法名称:" + methodName);

                                    System.out.println("返回值类型" + method.getReturnType());

                                    Class<?>[] parameterTypes = method.getParameterTypes();
                                    for (Class<?> clas : parameterTypes) {
                                        System.out.println("参数类型:" + clas);
                                        // 将参数类型加入该方法的参数类型数组中
                                        paramTypes.add(WebUtils.parseClassToString(clas));

                                    }
                                    mapMethod.put(curMethod.getMethodName(), curMethod);
                                    mapParams.put(curMethod.getMethodName(), paramTypes);
                                    System.out.println("==========================");
                                }
                                // 三、返回json字符串
//                                 req.setAttribute("mapMethod", mapMethod);
                                Gson gson = new Gson();
                                // 方法信息返回
                                mapData.put("methods", mapMethod);
                                mapData.put("params", mapParams);
                                mapData.put("filePath", filePath);
                                String mapDataString = gson.toJson(mapData);
                                resp.getWriter().write(mapDataString);
                                return;
                            } catch (Exception e1) {
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 模型注册
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        SmartUpload su = new SmartUpload();
        try {
            /*添加模型*/
            su.initialize(this.getServletConfig(), req, resp);
            su.upload();
            System.out.println("完成upload");
            String modelName = su.getRequest().getParameter("modelName");
            String modelType = su.getRequest().getParameter("modelType");
            String modelDescription = su.getRequest().getParameter("modelDescription");
            String version = su.getRequest().getParameter("version");
            String runtimeEnvironment = su.getRequest().getParameter("runtimeEnvironment");

            //模型标签
            String[] tags = su.getRequest().getParameterValues("modelTags");
            String modelTags = "";
            for (int i = 0; i < tags.length; i++) {
                modelTags += tags[i] + ";";// 用-分割每个标签
            }
            String developmentLanguage = su.getRequest().getParameter("developmentLanguage");
            // 应用领域
            String[] fields = su.getRequest().getParameterValues("applicationField");
            String applicationField = "";
            for (int i = 0; i < fields.length; i++) {
                applicationField += fields[i] + ";";// 用-分割每个应用领域
            }

            String createDepartment = su.getRequest().getParameter("createDepartment");
            String completionDepartment = su.getRequest().getParameter("completionDepartment");
            Date completionDate = WebUtils.parseDate(su.getRequest().getParameter("completionDate"));
            String testDescription = su.getRequest().getParameter("testDescription");
            String modelDirectoryCode = su.getRequest().getParameter("modelDirectoryCode");
            String attachmentDescription = su.getRequest().getParameter("attachmentDescription");
            Integer registerUserId = user.getId();
            // 存储jar包
            File file = su.getFiles().getFile(0);

            // 删除upload里面缓存的文件
            new java.io.File("E:\\Desktop\\Programmer\\JavaFile\\MyWeb\\modelManagementSystem\\web\\file\\upload\\" + file.getFileName()).delete();

            // jar上传的命名：用户名_文件名字
            String modelFileName = user.getUsername() + "_" + file.getFileName();
            String modelFilePath = "E:\\Desktop\\Programmer\\JavaFile\\MyWeb\\modelManagementSystem\\web\\file\\model\\" + modelFileName;
            file.saveAs(modelFilePath);
            // 存储模型附件
            file = su.getFiles().getFile(1);
            String attachmentPath = "E:\\Desktop\\Programmer\\JavaFile\\MyWeb\\modelManagementSystem\\web\\file\\attachment\\" + user.getUsername() + "_" + file.getFileName();
            file.saveAs(attachmentPath);
            modelService.addModel(new Model(null,
                    modelName,
                    modelType,
                    modelDescription,
                    version,
                    modelDirectoryCode,
                    runtimeEnvironment,
                    modelTags,
                    developmentLanguage,
                    applicationField,
                    modelFileName,
                    modelFilePath,
                    createDepartment,
                    null,
                    completionDepartment,
                    completionDate,
                    testDescription,
                    attachmentPath,
                    attachmentDescription,
                    "未发布",
                    0,
                    0,
                    new Date(),
                    null,
                    registerUserId));

            /*添加方法：暂时只写一个方法*/
            int modelId = modelService.queryModelByName(modelName).getId();
            String methodName = su.getRequest().getParameter("methodName");
            String methodCallName = su.getRequest().getParameter("methodCallName");
            String returnType = su.getRequest().getParameter("returnType");
            String methodDescription = su.getRequest().getParameter("methodDescription");
            String implementationClass = su.getRequest().getParameter("implementationClass");
            int parameterCount = WebUtils.parseInt(su.getRequest().getParameter("paramCount"), 0);
            String expectedResult = su.getRequest().getParameter("expectedResult");

            methodService.addMethod(new Method(null, methodName, methodCallName, modelId, returnType, methodDescription, parameterCount, expectedResult, implementationClass));

            // 参数信息
            int methodId = methodService.queryMethodByMethodNameAndModelId(methodName, modelId).getId();
            String[] parameterNameList = su.getRequest().getParameterValues("paramName");
            String[] parameterTypeList = su.getRequest().getParameterValues("paramType");
            String[] paramSampleList = su.getRequest().getParameterValues("paramSample");
            String[] paramDescriptionList = su.getRequest().getParameterValues("paramDescription");

            for (int i = 0; i < parameterNameList.length; i++) {
                parameterDao.saveParameter(new Parameter(null, parameterNameList[i], modelId, methodId, parameterTypeList[i], paramDescriptionList[i], null, null, paramSampleList[i]));
            }

            System.out.println("模型、方法、参数添加成功");

            // 添加模型注册记录
            String recordType = "release";
            Record record = new Record(null, modelId, modelName, recordType, user.getId(), new Date(), null, "待审核", null, null, null);
            recordService.addRecord(record);

            // 页面跳转
            pageForModelQuery(req, resp);
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
    }
}
