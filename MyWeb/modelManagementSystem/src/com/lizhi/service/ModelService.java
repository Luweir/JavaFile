package com.lizhi.service;

import com.lizhi.pojo.Method;
import com.lizhi.pojo.Model;
import com.lizhi.pojo.Page;

import java.util.List;

public interface ModelService {
    /**
     * 模型注册
     *
     * @param model
     */
    public void addModel(Model model);

    // 根据 name 查找model
    public Model queryModelByName(String name);

    // 根据 关键字 key 查找并返回模型数组
    public List<Model> queryModelsByKey(String key);

    // 获取所有模型
    public List<Model> queryAllNormalModel();

    // 根据模型状态获取模型
    public List<Model> queryModelsByState(String state);

    // 根据模型ID返回模型
    public Model queryModelByModelId(int modelId);

    // 修改模型信息
    public void updateModel(Model model);

    // 获得所有模型
    public List<Model> getAllModels();

    // 删除跟id=modelId模型相关的任何项
    public void deleteModel(int modelId);

    List<Model> getRecommendModels(Integer userId);

//    Page<Model> page(int pageNo, int pageSize);

    List<Model> getModelsByDirectoryCode(String  modelDirectoryCode);

    void updateModelDirectoryCode(String oldName, String newName);

    Page<Model> page(int pageNo, int pageSize);
}
