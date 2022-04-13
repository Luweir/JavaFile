package com.lizhi.dao;

import com.lizhi.pojo.Model;

import java.util.Date;
import java.util.List;

public interface ModelDao {
    // 根据模型ID查找某个模型
    public Model queryModelById(Integer id);

    // 根据模型名称查找某个模型
    public Model queryModelByName(String modelName);

    // 根据类型查找返回模型数组
    public List<Model> queryModelsByType(String modelType);

    // 根据状态获取模型数组
    public List<Model> queryModelsByState(String modelState);

    // 获取所有模型
    public List<Model> queryAllNormalModel();

    // 根据发布日期 查找并返回 区间模型数组
    public List<Model> queryModelsByDate(Date data1, Date data2);

    // 保存模型
    public int saveModel(Model model);

    // 更新模型信息
    public void updateModel(Model model);

    public List<Model> queryAllModel();

    public void deleteByModelId(int modelId);

    public void updateByRegisterId(int userId);

    public List<Model> queryReleasedModelsByDownload();

    public List<Model> queryModelsByDirectoryCode(String modelDirectoryCode);

    void updateModelDirectoryCode(String oldName, String newName);

    int queryForPageTotalCount();

    List<Model> queryPageItems(int begin, int pageSize);
}
