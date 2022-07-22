package com.lizhi.dao.impl;

import com.lizhi.dao.RecordDao;
import com.lizhi.pojo.Record;

import java.util.List;

public class RecordDaoImpl extends BaseDao implements RecordDao {
    @Override
    public int saveRecord(Record record) {
        String sql = "insert into t_record(`modelId`,`modelName`,`recordType`,`applicantId`,`applyDate`,`applyReason`,`recordState`,`reviewDate`,`reviewerId`,`reviewOpinion`) values(?,?,?,?,?,?,?,?,?,?)";
        return update(sql, record.getModelId(), record.getModelName(), record.getRecordType(), record.getApplicantId(), record.getApplyDate(), record.getApplyReason(), record.getRecordState(), record.getReviewDate(), record.getReviewerId(), record.getReviewOpinion());
    }

    @Override
    public List<Record> queryRecordsByType(String recordType) {
        return null;
    }

    @Override
    public List<Record> queryRecordsByApplicantId(int applicantId) {
        return null;
    }

    @Override
    public List<Record> queryRecordsByState(String state) {
        String sql = "select `id`,`modelId`,`modelName`,`recordType`,`applicantId`,`applyDate`,`applyReason`,`recordState`,`reviewDate`,`reviewerId`,`reviewOpinion` from t_record where recordState=?";
        return queryForList(sql, Record.class, state);
    }

    @Override
    public Record queryRecordByRecordId(Integer id) {
        String sql = "select `id`,`modelId`,`modelName`,`recordType`,`applicantId`,`applyDate`,`applyReason`,`recordState`,`reviewDate`,`reviewerId`,`reviewOpinion` from t_record where id=?";

        return queryForOne(sql, Record.class, id);
    }

    /**
     * 审核完后更新record部分数据
     *
     * @param record
     */
    @Override
    public void updateRecord(Record record) {
        String sql = "update t_record set `recordState`=?,`reviewDate`=?,`reviewerId`=?,`reviewOpinion`=? where id=?";
        update(sql, record.getRecordState(), record.getReviewDate(), record.getReviewerId(), record.getReviewOpinion(), record.getId());
    }

    /**
     * 返回指定管理员的已审核记录
     *
     * @return
     */
    @Override
    public List<Record> queryDoneRecordsByReviewerId(int reviewerId) {
        String sql = "select `id`,`modelId`,`modelName`,`recordType`,`applicantId`,`applyDate`,`applyReason`,`recordState`,`reviewDate`,`reviewerId`,`reviewOpinion` from t_record where `recordState`!='已审核' && `reviewerId`=?";
        return queryForList(sql, Record.class, reviewerId);
    }

    @Override
    public List<Record> queryAllRecordByUserId(int userId) {
        String sql = "select `id`,`modelId`,`modelName`,`recordType`,`applicantId`,`applyDate`,`applyReason`,`recordState`,`reviewDate`,`reviewerId`,`reviewOpinion` from t_record where `applicantId`=?";
        return queryForList(sql, Record.class, userId);
    }


    @Override
    public List<Record> queryRecordsByApplicantIdAndModelId(int applicantId, int modelId) {
        String sql = "select `id`,`modelId`,`modelName`,`recordType`,`applicantId`,`applyDate`,`applyReason`,`recordState`,`reviewDate`,`reviewerId`,`reviewOpinion` from t_record where `applicantId`=? && `modelId`=?";
        return queryForList(sql, Record.class, applicantId, modelId);
    }

    @Override
    public void deleteByModelId(int modelId) {
        String sql="delete from t_record where `modelId`=?";
        update(sql, modelId);
    }

    @Override
    public void deleteByUserId(int userId) {
        String sql="delete from t_record where `applicantId`=? or `reviewerId`=?";
        update(sql,userId,userId);
    }
}
