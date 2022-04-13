package com.lizhi.test;

import com.lizhi.dao.ModelDao;
import com.lizhi.dao.impl.ModelDaoImpl;
import com.lizhi.pojo.Model;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ModelDaoImplTest {

    @Test
    public void test() {
        ModelDao modelDao = new ModelDaoImpl();
        List<Model> models = modelDao.queryAllNormalModel();
        for (int i = 0; i < models.size(); i++) {
            System.out.println(models.get(i));
        }
    }

    @Test
    public void queryModelsByState() {
        ModelDao modelDao = new ModelDaoImpl();
        List<Model> modelList = modelDao.queryModelsByState("未发布");
        for (int i = 0; i < modelList.size(); i++) {
            System.out.println(modelList.get(i));
        }
    }

    @Test
    public void queryForPageTotalCount() {
        ModelDao modelDao = new ModelDaoImpl();
        System.out.println(modelDao.queryForPageTotalCount());
    }

    @Test
    public void queryPageItems() {
        ModelDao modelDao = new ModelDaoImpl();
        List<Model> models = modelDao.queryPageItems(0, 4);
        for (int i = 0; i < models.size(); i++) {
            System.out.println(models.get(i));
        }
    }
}