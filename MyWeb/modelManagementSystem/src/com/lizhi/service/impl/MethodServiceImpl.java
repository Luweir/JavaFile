package com.lizhi.service.impl;

import com.lizhi.dao.MethodDao;
import com.lizhi.dao.ParameterDao;
import com.lizhi.dao.impl.MethodDaoImpl;
import com.lizhi.dao.impl.ParameterDaoImpl;
import com.lizhi.pojo.Method;
import com.lizhi.service.MethodService;

import java.util.List;

public class MethodServiceImpl implements MethodService {
    private MethodDao methodDao = new MethodDaoImpl();

    /**
     * 添加method
     *
     * @param method
     */
    @Override
    public void addMethod(Method method) {
        methodDao.saveMethod(method);
    }

    @Override
    public Method queryMethodByMethodNameAndModelId(String methodName, int modelId) {
        return methodDao.queryMethodByMethodNameAndModelId(methodName, modelId);
    }

    @Override
    public List<Method> queryMethodsByModelId(int modelId) {
        return methodDao.queryMethodsByModelId(modelId);

    }

    @Override
    public List<Method> existMethodCallName(String methodCallName) {
        return methodDao.queryMethodByMethodCallName(methodCallName);
    }


}
