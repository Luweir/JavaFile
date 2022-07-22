package com.lizhi.service;

import com.lizhi.pojo.Method;
import com.lizhi.pojo.Record;

import java.util.List;

public interface RecordService {
    // 新增一条记录
    public void addRecord(Record record);

    // 根据状态返回记录
    public List<Record> queryRecordsByState(String state);

    // 根据记录ID查询记录
    public Record queryRecordByRecordId(Integer recordId);

    // 更新记录
    public void updateRecord(Record record);

    // 返回所有审核完毕的记录
    public List<Record> queryDoneRecordsByReviewerId(int reviewerId);

    // 返回指定用户id下的申请记录（无论是否成功）
    public List<Record> queryAllRecordByUserId(int userId);

    // 返回指定注册用户id 和 指定模型id 对应的最新的模型发布待审核记录
    public Record getRecordByRegisterIdAndModelId(int registerId, int modelId);
}
