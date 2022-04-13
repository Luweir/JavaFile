package com.lizhi.test;

import com.lizhi.pojo.Model;
import com.lizhi.pojo.Page;
import com.lizhi.service.ModelService;
import com.lizhi.service.impl.ModelServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelServiceTest {

    @Test
    public void page() {
        ModelService modelService = new ModelServiceImpl();
        Page<Model> page = modelService.page(1, Page.PAGE_SIZE);
//        for (int i = 0; i < page.getItems().size(); i++) {
//            System.out.println(page.getItems().get(i));
//        }
        System.out.println(page);
    }
}