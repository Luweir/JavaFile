package com.lizhi.service.impl;

import com.lizhi.dao.RecordDao;
import com.lizhi.dao.impl.RecordDaoImpl;
import com.lizhi.pojo.Record;
import com.lizhi.service.RecordService;

import java.util.List;

public class RecordServiceImpl implements RecordService {
    private RecordDao recordDao = new RecordDaoImpl();

    /**
     * 新增一条记录
     *
     * @param record
     */
    @Override
    public void addRecord(Record record) {
        recordDao.saveRecord(record);
    }

    /**
     * 返回指定状态的记录
     *
     * @return
     */
    @Override
    public List<Record> queryRecordsByState(String state) {

        return recordDao.queryRecordsByState(state);
    }

    /**
     * 根据记录ID查询记录
     *
     * @param recordId
     * @return
     */
    @Override
    public Record queryRecordByRecordId(Integer recordId) {
        return recordDao.queryRecordByRecordId(recordId);
    }

    /**
     * 更新记录
     *
     * @param record
     */
    @Override
    public void updateRecord(Record record) {
        recordDao.updateRecord(record);
    }

    @Override
    public List<Record> queryDoneRecordsByReviewerId(int reviewerId) {
        return recordDao.queryDoneRecordsByReviewerId(reviewerId);
    }

    /**
     * 根据用户id找到所有模型申请记录
     *
     * @param userId
     * @return
     */
    @Override
    public List<Record> queryAllRecordByUserId(int userId) {
        return recordDao.queryAllRecordByUserId(userId);
    }


    @Override
    public Record getRecordByRegisterIdAndModelId(int registerId, int modelId) {
        // 1 拿到所有 applicantId=registerId 并且 modelId=modelId 的record
        List<Record> recordList = recordDao.queryRecordsByApplicantIdAndModelId(registerId, modelId);
        // 2 筛选 要是模型发布类型的记录，并且是未审核
        for (int i = recordList.size() - 1; i >= 0; i--) {
            // 只要是未发布的模型，必然有最新的一条记录是待审核
            if ("release".equals(recordList.get(i).getRecordType()) && "待审核".equals(recordList.get(i).getRecordState())) {
                return recordList.get(i);
            }
        }
        return null;
    }
}
