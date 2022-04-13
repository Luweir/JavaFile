package com.lizhi.web;

import com.lizhi.dao.ParameterDao;
import com.lizhi.dao.impl.ParameterDaoImpl;
import com.lizhi.pojo.*;
import com.lizhi.pojo.Record;
import com.lizhi.service.*;
import com.lizhi.service.impl.*;
import com.lizhi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class RecordServlet extends BaseServlet {
    private RecordService recordService = new RecordServiceImpl();
    private ModelService modelService = new ModelServiceImpl();
    private MethodService methodService = new MethodServiceImpl();
    private DownloadService downloadService = new DownloadServiceImpl();
    private ParameterDao parameterDao = new ParameterDaoImpl();
    private UserService userService = new UserServiceImpl();


    /**
     * 模型发布页面中的快速发布模型功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void quickRelease(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Model model = modelService.queryModelByModelId(WebUtils.parseInt(req.getParameter("modelId"), 0));
        if (model != null) {
            int registerId = model.getRegisterUserId();
            Record record = recordService.getRecordByRegisterIdAndModelId(registerId, model.getId());
            if (record != null) {
                record.setReviewerId(user.getId());
                record.setReviewDate(new Date());
                record.setRecordState("通过");
                model.setModelState("已发布");
                model.setReleaseDate(new Date());
                modelService.updateModel(model);
                recordService.updateRecord(record);
                resp.getWriter().write("true");
            } else {
                resp.getWriter().write("false");
            }
        }
    }


    /**
     * 用户提交评分，保存信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void submitScore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int downloadId = WebUtils.parseInt(req.getParameter("downloadId"), 0);
        Download download = downloadService.getDownloadById(downloadId);

        float score = WebUtils.parseFloat(req.getParameter("score"), -1);
        if (score != -1) {
            download.setScore(Math.round(score));
            downloadService.updateDownload(download);
            // 统计该模型的所有得分信息，计算出整体得分，然后修改该模型的得分信息
            List<Download> downloads = downloadService.getAllDownloadsByModelId(download.getModelId());
            float sum = 0;
            int count = 0;
            for (int i = 0; i < downloads.size(); i++) {
                if (downloads.get(i).getScore() != null) {
                    sum += downloads.get(i).getScore();
                    count++;
                }
            }
            Model model = modelService.queryModelByModelId(download.getModelId());
            model.setScore(Math.round(sum / count));
            modelService.updateModel(model);
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }

    /**
     * 显示用户待评价/已评价信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void myEvaluation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        1、获得此用户download条项信息；
        User user = (User) req.getSession().getAttribute("user");
        List<Download> downloads = userService.getDownloadByUser(user);
        //        2、 传递参数
        req.setAttribute("downloads", downloads);
        req.getRequestDispatcher("/pages/business/evaluate.jsp").forward(req, resp);
    }

    /**
     * 显示用户可下载模型
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showCanDownload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、获得此用户的download条项信息；
        User user = (User) req.getSession().getAttribute("user");
        List<Download> downloadList = userService.getDownloadByUser(user);
        List<Download> downloads = new ArrayList<>();

        String modelName = req.getParameter("modelName") == "" ? null : req.getParameter("modelName");
        String modelType = req.getParameter("modelType") == "" ? null : req.getParameter("modelType");
        String keyWord = req.getParameter("keyWord") == "" ? null : req.getParameter("keyWord");
        Date startReleaseDate = WebUtils.parseDate(req.getParameter("startReleaseDate"));
        Date endReleaseDime = WebUtils.parseDate(req.getParameter("endReleaseDime"));

        downloadList.forEach(item -> {
            if ((modelName == null || modelName.equals(item.getModelName())) && (modelType == null || modelType.equals(item.getModelType()))) {
                if (keyWord == null || item.getModelName().contains(keyWord)) {
                    if (startReleaseDate == null || startReleaseDate.getTime() < item.getReleaseDate().getTime()) {
                        if (endReleaseDime == null || endReleaseDime.getTime() > item.getReleaseDate().getTime()) {
                            downloads.add(item);
                        }
                    }
                }

            }
        });

//        2、返回数据
        req.setAttribute("downloads", downloads);
        req.getRequestDispatcher("/pages/model/model_download.jsp").forward(req, resp);
    }

    /**
     * 返回我的申请记录
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void myApplyRecord(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Record> recordList = recordService.queryAllRecordByUserId(user.getId());
        for (int i = 0; i < recordList.size(); i++) {
            Model model = modelService.queryModelByModelId(recordList.get(i).getModelId());
            recordList.get(i).setApplicantName(user.getUsername());
            recordList.get(i).setModelName(model.getModelName());
        }
        req.setAttribute("records", recordList);
        req.getRequestDispatcher("/pages/business/apply.jsp").forward(req, resp);
    }

    /**
     * 管理员/用户点击查看记录，获得记录详细信息，跳转到记录详情页
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void recordDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取记录 ID
        int recordId = WebUtils.parseInt(req.getParameter("recordId"), 0);
        // 2 查找记录，得到详细信息
        Record record = recordService.queryRecordByRecordId(recordId);
        record.setApplicantName(userService.getUserByUserId(record.getApplicantId()).getUsername());
        Model model = modelService.queryModelByModelId(record.getModelId());
        // 3 把record存在req域里面
        req.setAttribute("record", record);
        req.setAttribute("model", model);
        // 4 请求转发
        req.getRequestDispatcher("/pages/record/record_detail.jsp").forward(req, resp);
    }

    /**
     * 管理员审核后，提交到这，修改记录状态
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateRecord(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("updateRecord");
        int recordId = WebUtils.parseInt(req.getParameter("recordId"), 0);
        Record record = recordService.queryRecordByRecordId(recordId);
        User user = (User) req.getSession().getAttribute("user");

        // 向record中修改审核信息
        record.setReviewerId(user.getId());
        record.setRecordState(req.getParameter("recordState").equals("agree") ? "通过" : "拒绝");
        record.setReviewOpinion(req.getParameter("reviewOpinion"));
        record.setReviewDate(new Date());

        Model model = modelService.queryModelByModelId(record.getModelId());
        // 如果记录类型是发布模型 并且 已通过，则修改模型信息
        if (record.getRecordType().equals("release")) {

            if (record.getRecordState().equals("通过")) {
                model.setReleaseDate(new Date());
                model.setModelState("已发布");
            } else {
                model.setModelState("拒绝发布");
            }
            modelService.updateModel(model);
        }
        // 如果记录类型是申请模型 并且已通过，则修改t_download表项，即添加用户可下载模型
        if (record.getRecordType().equals("apply") && req.getParameter("recordState").equals("agree")) {
            Download download = new Download(null, model.getId(), record.getApplicantId(), null, null);
            downloadService.saveDownloadItem(download);
        }
        // 更新 record
        recordService.updateRecord(record);
        resp.sendRedirect(req.getContextPath() + "/recordServlet?action=recordListForWaiting");
    }


    /**
     * 管理员点击我的待办，显示所有待审核记录，包括模型申请
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void recordListForWaiting(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Record> recordList = recordService.queryRecordsByState("待审核");
        List<Record> records = new ArrayList<>();

        for (int i = 0; i < recordList.size(); i++) {
            User user = userService.getUserByUserId(recordList.get(i).getApplicantId());
            recordList.get(i).setApplicantName(user.getUsername());
        }
        String modelName = req.getParameter("modelName") == "" ? null : req.getParameter("modelName");
        Date startApplyDate = WebUtils.parseDate(req.getParameter("startApplyDate"));
        Date endApplyDate = WebUtils.parseDate(req.getParameter("endApplyDate"));

        recordList.forEach(item -> {
            if (modelName == null || modelName.equals(item.getModelName())) {
                if (startApplyDate == null || startApplyDate.getTime() <= item.getApplyDate().getTime()) {
                    if (endApplyDate == null || endApplyDate.getTime() >= item.getApplyDate().getTime()) {
                        records.add(item);
                    }
                }
            }
        });

        req.setAttribute("records", records);
        req.getRequestDispatcher("/pages/business/agenda.jsp").forward(req, resp);
    }

    /**
     * 管理员点击我的已办，返回该管理员下的除了未审核以外的记录
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void recordListForDone(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Record> recordList = recordService.queryDoneRecordsByReviewerId(user.getId());
        for (int i = 0; i < recordList.size(); i++) {
            String applicantName = userService.getUserByUserId(recordList.get(i).getApplicantId()).getUsername();
            String modelName = modelService.queryModelByModelId(recordList.get(i).getModelId()).getModelName();
            recordList.get(i).setApplicantName(applicantName);
            recordList.get(i).setReviewerName(user.getUsername());
            recordList.get(i).setModelName(modelName);
        }
        req.setAttribute("records", recordList);
        req.getRequestDispatcher("/pages/business/done.jsp").forward(req, resp);
    }

    /**
     * 用户填写完申请信息，点击申请按钮
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void modelApply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer modelId = WebUtils.parseInt(req.getParameter("modelId"), 0);
        Model model = modelService.queryModelByModelId(modelId);
        User user = (User) req.getSession().getAttribute("user");
        Record record = new Record(null, modelId, model.getModelName(), "apply", user.getId(), new Date(), req.getParameter("applyReason"), "待审核", null, null, null);
        recordService.addRecord(record);
        System.out.println("新增记录成功");
        // 返回模型搜索页面
        req.getRequestDispatcher("/modelServlet?action=modelListForReleased").forward(req, resp);
    }

    /**
     * 集模型详情、模型申请、模型申请审核、模型发布审核为一体，以operationType作为区分
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void modelDetailApplyAudit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operationType = req.getParameter("operationType");
        Integer modelId = WebUtils.parseInt(req.getParameter("modelId"), 0);
        Model model = modelService.queryModelByModelId(modelId);
        req.setAttribute("model", model);

        // 设置操作为模型申请
        if ("apply".equals(operationType)) {
            req.setAttribute("operationType", "apply");
        } else {
            // 其他情况
            req.setAttribute("filePath", model.getModelFilePath().replace("\\", "\\\\"));
            Map<Method, List<Parameter>> methodListMap = new HashMap<>();
            // 对模型发布申请进行审核,需要传模型记录和方法还有参数数组,如果是使用申请进行审核，则不用传入方法和参数
            List<Method> methods = methodService.queryMethodsByModelId(model.getId());
            for (int i = 0; i < methods.size(); i++) {
                List<Parameter> parameterList = parameterDao.queryParametersByMethodId(methods.get(i).getId());
                methodListMap.put(methods.get(i), parameterList);
            }
            req.setAttribute("methods", methodListMap);

            // 模型审核
            if ("audit".equals(operationType)) {
                System.out.println("modelAudit");
                Integer recordId = WebUtils.parseInt(req.getParameter("recordId"), 0);
                Record record = recordService.queryRecordByRecordId(recordId);
                User user = userService.getUserByUserId(record.getApplicantId());
                record.setApplicantName(user.getUsername());
                req.setAttribute("record", record);
                // 如果是模型发布审核
                if (record.getRecordType().equals("release")) {
                    req.setAttribute("operationType", "releaseAudit");
                } else {
                    // 如果是模型申请审核
                    req.setAttribute("operationType", "applyAudit");
                }
            }
            // 模型详情
            if ("detail".equals(operationType)) {
                req.setAttribute("operationType", "detail");
            }
        }
        // 只需要传模型和记录
        req.getRequestDispatcher("/pages/model/model_detail_apply_audit.jsp").forward(req, resp);
    }
}
