package com.lizhi.service.impl;

import com.lizhi.dao.*;
import com.lizhi.dao.impl.*;
import com.lizhi.pojo.*;
import com.lizhi.service.ModelService;

import java.util.ArrayList;
import java.util.List;

public class ModelServiceImpl implements ModelService {
    private ModelDao modelDao = new ModelDaoImpl();

    /**
     * 注册模型
     *
     * @param model
     */
    @Override
    public void addModel(Model model) {
        modelDao.saveModel(model);
    }

    /**
     * 根据模型名查找模型
     *
     * @param name
     * @return
     */
    public Model queryModelByName(String name) {
        return modelDao.queryModelByName(name);
    }

    /**
     * 根据关键字查找模型
     *
     * @param key
     * @return
     */
    @Override
    public List<Model> queryModelsByKey(String key) {
        return null;
    }

    /**
     * 获得数据库中所有未发布和已发布模型
     *
     * @return
     */
    @Override
    public List<Model> queryAllNormalModel() {
        return modelDao.queryAllNormalModel();
    }

    /**
     * 根据模型状态获取模型
     *
     * @param state
     * @return
     */
    @Override
    public List<Model> queryModelsByState(String state) {
        return modelDao.queryModelsByState(state);
    }

    /**
     * 根据模型id获取模型对象
     *
     * @param modelId
     * @return
     */
    @Override
    public Model queryModelByModelId(int modelId) {
        return modelDao.queryModelById(modelId);
    }

    /**
     * 修改模型信息
     *
     * @param model
     */
    @Override
    public void updateModel(Model model) {
        modelDao.updateModel(model);
    }

    @Override
    public List<Model> getAllModels() {
        return modelDao.queryAllModel();
    }

    @Override
    public void deleteModel(int modelId) {
        MethodDao methodDao = new MethodDaoImpl();
        RecordDao recordDao = new RecordDaoImpl();
        DownloadDao downloadDao = new DownloadDaoImpl();
        ParameterDao parameterDao = new ParameterDaoImpl();

        // 删除跟modelId有关的download
        downloadDao.deleteByModelId(modelId);
        parameterDao.deleteByModelId(modelId);
        recordDao.deleteByModelId(modelId);
        methodDao.deleteByModelId(modelId);
        modelDao.deleteByModelId(modelId);
    }

    @Override
    public List<Model> getRecommendModels(Integer userId) {
        DownloadDao downloadDao = new DownloadDaoImpl();
        List<Model> models = modelDao.queryReleasedModelsByDownload();
        List<Model> modelList = new ArrayList<>();
        if (models.size() < 5) {
            return models;
        } else {
            List<Download> downloads = downloadDao.queryDownloadsByUserId(userId);
            for (int i = 0; i < models.size(); i++) {
                int i1 = 0;
                for (; i1 < downloads.size(); i1++) {
                    if (downloads.get(i1).getModelId() == models.get(i).getId()) {
                        break;
                    }
                }
                if (i1 == downloads.size()) {
                    modelList.add(models.get(i));
                }
                if (modelList.size() == 5) {
                    break;
                }
            }
            return modelList;
        }
    }

    @Override
    public List<Model> getModelsByDirectoryCode(String modelDirectoryCode) {
        return modelDao.queryModelsByDirectoryCode(modelDirectoryCode);
    }

    @Override
    public void updateModelDirectoryCode(String oldName, String newName) {
        modelDao.updateModelDirectoryCode(oldName, newName);
    }

    @Override
    public Page<Model> page(int pageNo, int pageSize) {
        Page<Model> page = new Page<Model>();

        // 设置每页显示数量
        page.setPageSize(pageSize);
        // 求总的已发布模型数
        int pageTotalCount = modelDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        //设置当前页面
        page.setPageNo(pageNo);

        // 求当前页数据
        int begin = (page.getPageNo() - 1) * pageSize;//开始索引
        List<Model> items = modelDao.queryPageItems(begin, pageSize);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }


}
