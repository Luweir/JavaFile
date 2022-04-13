package com.lizhi.dao.impl;

import com.lizhi.dao.ModelDao;
import com.lizhi.pojo.Model;

import java.util.Date;
import java.util.List;

public class ModelDaoImpl extends BaseDao implements ModelDao {
    /**
     * 根据ID返回model对象
     *
     * @param id
     * @return
     */
    @Override
    public Model queryModelById(Integer id) {
        String sql = "select `id`,`modelName`,`modelType`,`modelDescription`,`version`,`modelDirectoryCode`,`runtimeEnvironment`," +
                "`modelTags`,`developmentLanguage`,`applicationField`,`modelFileName`,`modelFilePath`," +
                "`createDepartment`,`createDepartmentCode`,`completionDepartment`,`completionDate`,`testDescription`," +
                "`attachmentPath`,`attachmentDescription`,`modelState`,`downloads`,`score`,`registerDate`,`releaseDate`,`registerUserId` from t_model where id = ?";
        Model model = queryForOne(sql, Model.class, id);
        return model;
    }

    /**
     * 根据模型名称搜索模型并返回
     *
     * @param modelName
     * @return
     */
    @Override
    public Model queryModelByName(String modelName) {
        String sql = "select `id`,`modelName`,`modelType`,`modelDescription`,`version`,`modelDirectoryCode`,`runtimeEnvironment`," +
                "`modelTags`,`developmentLanguage`,`applicationField`,`modelFileName`,`modelFilePath`," +
                "`createDepartment`,`createDepartmentCode`,`completionDepartment`,`completionDate`,`testDescription`," +
                "`attachmentPath`,`attachmentDescription`,`modelState`,`downloads`,`score`,`registerDate`,`releaseDate`,`registerUserId` from t_model where `modelName` = ?";
        Model model = queryForOne(sql, Model.class, modelName);
        return model;
    }

    /**
     * 根据模型类型搜索模型数组并返回
     *
     * @param modelType
     * @return
     */
    @Override
    public List<Model> queryModelsByType(String modelType) {
        String sql = "select `id`,`modelName`,`modelType`,`modelDescription`,`version`,`modelDirectoryCode`,`runtimeEnvironment`," +
                "`modelTags`,`developmentLanguage`,`applicationField`,`modelFileName`,`modelFilePath`," +
                "`createDepartment`,`createDepartmentCode`,`completionDepartment`,`completionDate`,`testDescription`," +
                "`attachmentPath`,`attachmentDescription`,`modelState`,`downloads`,`score`,`registerDate`,`releaseDate`,`registerUserId` from t_model where `modelType` = ? ORDER BY `id` DESC";
        List<Model> models = queryForList(sql, Model.class, modelType);
        return models;
    }

    /**
     * 根据模型状态获取模型
     *
     * @param modelState
     * @return
     */
    @Override
    public List<Model> queryModelsByState(String modelState) {
        String sql = "select `id`,`modelName`,`modelType`,`modelDescription`,`version`,`modelDirectoryCode`,`runtimeEnvironment`," +
                "`modelTags`,`developmentLanguage`,`applicationField`,`modelFileName`,`modelFilePath`," +
                "`createDepartment`,`createDepartmentCode`,`completionDepartment`,`completionDate`,`testDescription`," +
                "`attachmentPath`,`attachmentDescription`,`modelState`,`downloads`,`score`,`registerDate`,`releaseDate`,`registerUserId` from t_model where modelState = ? ORDER BY `id` DESC";
        return queryForList(sql, Model.class, modelState);
    }

    /**
     * 按下载量和评分排序
     *
     * @return
     */
    public List<Model> queryReleasedModelsByDownload() {
        String sql = "select `id`,`modelName`,`modelType`,`modelDescription`,`version`,`modelDirectoryCode`,`runtimeEnvironment`," +
                "`modelTags`,`developmentLanguage`,`applicationField`,`modelFileName`,`modelFilePath`," +
                "`createDepartment`,`createDepartmentCode`,`completionDepartment`,`completionDate`,`testDescription`," +
                "`attachmentPath`,`attachmentDescription`,`modelState`,`downloads`,`score`,`registerDate`,`releaseDate`,`registerUserId` from t_model where `modelState` = '已发布' ORDER BY `downloads` DESC,`score` DESC";
        return queryForList(sql, Model.class);
    }

    @Override
    public List<Model> queryModelsByDirectoryCode(String modelDirectoryCode) {
        String sql = "select `id`,`modelName`,`modelType`,`modelDescription`,`version`,`modelDirectoryCode`,`runtimeEnvironment`," +
                "`modelTags`,`developmentLanguage`,`applicationField`,`modelFileName`,`modelFilePath`," +
                "`createDepartment`,`createDepartmentCode`,`completionDepartment`,`completionDate`,`testDescription`," +
                "`attachmentPath`,`attachmentDescription`,`modelState`,`downloads`,`score`,`registerDate`,`releaseDate`,`registerUserId` from t_model where `modelDirectoryCode` = ? and `modelState`='已发布' ORDER BY `id` DESC";
        return queryForList(sql, Model.class, modelDirectoryCode);
    }

    @Override
    public void updateModelDirectoryCode(String oldName, String newName) {
        String sql = "update t_model set `modelDirectoryCode`=? where `modelDirectoryCode`=?";
        update(sql, newName, oldName);
    }

    @Override
    public int queryForPageTotalCount() {
        String sql = "select count(*) from t_model where `modelState`='已发布'";
        Number number = getSpecialValues(sql);
        System.out.println(number.intValue());
        return number.intValue();
    }

    @Override
    public List<Model> queryPageItems(int begin, int pageSize) {
        String sql = "select `id`,`modelName`,`modelType`,`modelDescription`,`version`,`modelDirectoryCode`,`runtimeEnvironment`," +
                "`modelTags`,`developmentLanguage`,`applicationField`,`modelFileName`,`modelFilePath`," +
                "`createDepartment`,`createDepartmentCode`,`completionDepartment`,`completionDate`,`testDescription`," +
                "`attachmentPath`,`attachmentDescription`,`modelState`,`downloads`,`score`,`registerDate`,`releaseDate`,`registerUserId` from t_model where `modelState` = '已发布' ORDER BY `downloads` DESC,`score` DESC limit ?,?";
        return queryForList(sql, Model.class,begin,pageSize);
    }

    /**
     * 返回待发布、已发布的所有模型
     *
     * @return
     */
    @Override
    public List<Model> queryAllNormalModel() {
        String sql = "select `id`,`modelName`,`modelType`,`modelDescription`,`version`,`modelDirectoryCode`,`runtimeEnvironment`," +
                "`modelTags`,`developmentLanguage`,`applicationField`,`modelFileName`,`modelFilePath`," +
                "`createDepartment`,`createDepartmentCode`,`completionDepartment`,`completionDate`,`testDescription`," +
                "`attachmentPath`,`attachmentDescription`,`modelState`,`downloads`,`score`,`registerDate`,`releaseDate`,`registerUserId` from t_model where `modelState`='已发布' or `modelState`='未发布' ORDER BY `id` DESC ";
        return queryForList(sql, Model.class);
    }

    /**
     * 根据发布时间搜索模型
     *
     * @param data1
     * @param data2
     * @return
     */
    @Override
    public List<Model> queryModelsByDate(Date data1, Date data2) {
        String sql = "select `id`,`modelName`,`modelType`,`modelDescription`,`version`,`modelDirectoryCode`,`runtimeEnvironment`," +
                "`modelTags`,`developmentLanguage`,`applicationField`,`modelFileName`,`modelFilePath`," +
                "`createDepartment`,`createDepartmentCode`,`completionDepartment`,`completionDate`,`testDescription`," +
                "`attachmentPath`,`attachmentDescription`,`modelState`,`downloads`,`score`,`registerDate`,`releaseDate`,`registerUserId` from t_model where `releaseDate` between ? and ? ORDER BY `id` DESC";
        List<Model> models = queryForList(sql, Model.class, data1, data2);
        return models;
    }

    /**
     * 保存model
     *
     * @param model
     * @return
     */
    @Override
    public int saveModel(Model model) {
        String sql = "insert into t_model(`id`,`modelName`,`modelType`,`modelDescription`,`version`,`modelDirectoryCode`,`runtimeEnvironment`," +
                "`modelTags`,`developmentLanguage`,`applicationField`,`modelFileName`,`modelFilePath`," +
                "`createDepartment`,`createDepartmentCode`,`completionDepartment`,`completionDate`,`testDescription`," +
                "`attachmentPath`,`attachmentDescription`,`modelState`,`downloads`,`score`,`registerDate`,`releaseDate`,`registerUserId`) values(" +
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return update(sql, model.getId(), model.getModelName(), model.getModelType(), model.getModelDescription(), model.getVersion(), model.getModelDirectoryCode(), model.getRuntimeEnvironment(),
                model.getModelTags(), model.getDevelopmentLanguage(), model.getApplicationField(), model.getModelFileName(), model.getModelFilePath(),
                model.getCreateDepartment(), model.getCreateDepartmentCode(), model.getCompletionDepartment(), model.getCompletionDate(), model.getTestDescription(),
                model.getAttachmentPath(), model.getAttachmentDescription(), model.getModelState(), model.getDownloads(), model.getScore(), model.getRegisterDate(), model.getReleaseDate(), model.getRegisterUserId());
    }

    /**
     * 根据模型ID更新模型信息
     *
     * @param model
     */
    @Override
    public void updateModel(Model model) {
        String sql = "update t_model set `id`=?,`modelName`=?,`modelType`=?,`modelDescription`=?,`version`=?," +
                "`modelDirectoryCode`=?,`runtimeEnvironment`=?,`modelTags`=?,`developmentLanguage`=?,`applicationField`=?," +
                "`modelFileName`=?,`modelFilePath`=?,`createDepartment`=?,`createDepartmentCode`=?,`completionDepartment`=?," +
                "`completionDate`=?,`testDescription`=?,`attachmentPath`=?,`attachmentDescription`=?,`modelState`=?," +
                "`downloads`=?,`score`=?,`registerDate`=?,`releaseDate`=?,`registerUserId`=? where id=?";
        update(sql, model.getId(), model.getModelName(), model.getModelType(), model.getModelDescription(), model.getVersion(),
                model.getModelDirectoryCode(), model.getRuntimeEnvironment(), model.getModelTags(), model.getDevelopmentLanguage(), model.getApplicationField(),
                model.getModelFileName(), model.getModelFilePath(), model.getCreateDepartment(), model.getCreateDepartmentCode(), model.getCompletionDepartment(),
                model.getCompletionDate(), model.getTestDescription(), model.getAttachmentPath(), model.getAttachmentDescription(), model.getModelState(),
                model.getDownloads(), model.getScore(), model.getRegisterDate(), model.getReleaseDate(), model.getRegisterUserId(),
                model.getId());
    }

    @Override
    public List<Model> queryAllModel() {
        String sql = "select `id`,`modelName`,`modelType`,`modelDescription`,`version`,`modelDirectoryCode`,`runtimeEnvironment`," +
                "`modelTags`,`developmentLanguage`,`applicationField`,`modelFileName`,`modelFilePath`," +
                "`createDepartment`,`createDepartmentCode`,`completionDepartment`,`completionDate`,`testDescription`," +
                "`attachmentPath`,`attachmentDescription`,`modelState`,`downloads`,`score`,`registerDate`,`releaseDate`,`registerUserId` from t_model ORDER BY `id` DESC";
        List<Model> models = queryForList(sql, Model.class);
        return models;
    }

    @Override
    public void deleteByModelId(int modelId) {
        String sql = "delete from t_model where id=?";
        update(sql, modelId);

    }

    @Override
    public void updateByRegisterId(int userId) {
        String sql = "update t_model set `registerUserId`=? where `registerUserId`=?";
        update(sql, null, userId);
    }
}
