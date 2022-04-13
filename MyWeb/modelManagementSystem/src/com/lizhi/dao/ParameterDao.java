package com.lizhi.dao;

import com.lizhi.pojo.Parameter;

import java.util.List;

public interface ParameterDao {
    // 保存参数
    public void saveParameter(Parameter parameter);

    // 根据方法ID查找参数
    public List<Parameter> queryParametersByMethodId(int methodId);

    public void deleteByModelId(int modelId);
}
