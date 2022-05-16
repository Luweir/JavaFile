package com.kuang.dao;

import com.kuang.pojo.User;
import com.kuang.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {
    @Test
    public void test() {
        // 第一步：获得sqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            // 第二步：执行SQL
            // 方式一：getMapper
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            List<User> userList = mapper.getUserList();
            for (User user : userList) {
                System.out.println(user.toString());
            }
        } catch (Exception e) {

        } finally {
            // 关闭sqlSession
            sqlSession.close();
        }
    }
}
