package com.lizhi.service;

import com.lizhi.pojo.Method;

import java.util.List;

public interface MethodService {
    // 添加方法
    public void addMethod(Method method);

    // 根据模型id查找方法
    public Method queryMethodByMethodNameAndModelId(String methodName, int modelId);

    // 根据模型id查找方法
    public List<Method> queryMethodsByModelId(int modelId);

    public List<Method> existMethodCallName(String methodCallName);
}
