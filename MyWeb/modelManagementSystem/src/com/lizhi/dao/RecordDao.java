package com.lizhi.dao;

import com.lizhi.pojo.Record;

import java.security.PublicKey;
import java.util.List;

public interface RecordDao {
    // 保存一条记录
    public int saveRecord(Record record);

    // 根据记录类型查找记录
    public List<Record> queryRecordsByType(String recordType);

    // 根据申请人查找记录
    public List<Record> queryRecordsByApplicantId(int applicantId);

    // 根据记录状态返回记录数组
    public List<Record> queryRecordsByState(String state);

    // 根据记录ID返回记录
    public Record queryRecordByRecordId(Integer id);

    // 修改Record
    public void updateRecord(Record record);

    // 返回指定用户名（管理员）的已审核记录
    public List<Record> queryDoneRecordsByReviewerId(int reviewerId);

    // 根据用户名获取所有申请记录
    public List<Record> queryAllRecordByUserId(int userId);


    // 返回指定用户发起者id 指定模型id 的所有记录
    public List<Record> queryRecordsByApplicantIdAndModelId(int applicantId, int modelId);

    public  void deleteByModelId(int modelId);

    public void deleteByUserId(int userId);
}
