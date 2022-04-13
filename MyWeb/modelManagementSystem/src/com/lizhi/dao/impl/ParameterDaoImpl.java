package com.lizhi.dao.impl;

import com.lizhi.dao.ParameterDao;
import com.lizhi.pojo.Parameter;

import java.util.List;

public class ParameterDaoImpl extends BaseDao implements ParameterDao {
    /**
     * 保存参数
     *
     * @param parameter
     */
    @Override
    public void saveParameter(Parameter parameter) {
        String sql = "insert into t_parameter(`id`,`paramName`,`modelId`,`methodId`,`paramType`," +
                "`paramDescription`,`isJson`,`arrayLength`,`paramSample`) values(?,?,?,?,?,?,?,?,?)";
        update(sql, parameter.getId(), parameter.getParamName(), parameter.getModelId(), parameter.getMethodId(),
                parameter.getParamType(), parameter.getParamDescription(), parameter.getIsJson(), parameter.getArrayLength(), parameter.getParamSample());
    }

    /**
     * 根据方法ID查找参数
     *
     * @param methodId
     * @return
     */
    @Override
    public List<Parameter> queryParametersByMethodId(int methodId) {
        String sql = "select `id`,`paramName`,`modelId`,`methodId`,`paramType`," +
                "`paramDescription`,`isJson`,`arrayLength`,`paramSample` from t_parameter where methodId=?";
        return queryForList(sql, Parameter.class, methodId);
    }

    @Override
    public void deleteByModelId(int modelId) {
        String sql = "delete from t_parameter where modelId=?";
        update(sql, modelId);
    }
}
