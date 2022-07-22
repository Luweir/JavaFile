package com.lizhi.dao;

import com.lizhi.pojo.Method;

import java.util.List;

public interface MethodDao {
    // 保存方法
    public int saveMethod(Method method);

    // 根据模型id和方法名查找方法
    public Method queryMethodByMethodNameAndModelId(String methodName, int modelId);

    // 根据模型ID查找方法
    public List<Method> queryMethodsByModelId(int modelId);

    // 根据方法调用名查找方法
    public List<Method> queryMethodByMethodCallName(String methodCallName);

    public void deleteByModelId(int modelId);
}
