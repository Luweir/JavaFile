package com.lizhi.dao.impl;

import com.lizhi.dao.MethodDao;
import com.lizhi.pojo.Method;

import java.util.List;

public class MethodDaoImpl extends BaseDao implements MethodDao {
    /**
     * 保存方法
     *
     * @param method
     * @return
     */
    @Override
    public int saveMethod(Method method) {
        String sql = "insert into t_method(`id`,`methodName`,`methodCallName`,`modelId`," +
                "`returnType`,`methodDescription`,`parameterCount`,`expectedResult`,`implementationClass`) values(?,?,?,?,?,?,?,?,?)";
        return update(sql, method.getId(), method.getMethodName(), method.getMethodCallName(), method.getModelId(), method.getReturnType(), method.getMethodDescription(), method.getParameterCount(), method.getExpectedResult(), method.getImplementationClass());
    }

    @Override
    public Method queryMethodByMethodNameAndModelId(String methodName, int modelId) {
        String sql = "select `id`,`methodName`,`methodCallName`,`modelId`," +
                "`returnType`,`methodDescription`,`parameterCount`,`expectedResult`,`implementationClass` from t_method where methodName=? && modelId=? ";
        return queryForOne(sql, Method.class, methodName, modelId);
    }

    /**
     * 根据模型ID查找方法
     *
     * @param modelId
     * @return
     */
    @Override
    public List<Method> queryMethodsByModelId(int modelId) {
        String sql = "select `id`,`methodName`,`methodCallName`,`modelId`," +
                "`returnType`,`methodDescription`,`parameterCount`,`expectedResult`,`implementationClass` from t_method where modelId=? ";
        return queryForList(sql, Method.class, modelId);
    }

    @Override
    public List<Method> queryMethodByMethodCallName(String methodCallName) {
        String sql = "select `id`,`methodName`,`methodCallName`,`modelId`," +
                "`returnType`,`methodDescription`,`parameterCount`,`expectedResult`,`implementationClass` from t_method where methodCallName=? ";
        return queryForList(sql, Method.class, methodCallName);
    }

    @Override
    public void deleteByModelId(int modelId) {
        String sql = "delete from t_method where modelId=?";
        update(sql, modelId);

    }
}
