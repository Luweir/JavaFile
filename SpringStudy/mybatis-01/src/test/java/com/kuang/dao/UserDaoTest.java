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
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
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

    @Test
    public void test2() {
        // 第一步：获得sqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            // 第二步：执行SQL
            // 方式一：getMapper
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User userList = mapper.getUserById(1);
            System.out.println(userList.toString());
        } catch (Exception e) {

        } finally {
            // 关闭sqlSession
            sqlSession.close();
        }
    }

    /**
     * User{id=1, name='狂神', pwd='123421412'}
     */
    @Test
    public void Test3() {
        // 第一步：获得sqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            // 第二步：执行SQL
            // 方式一：getMapper
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int i = mapper.addUser(new User(0, "麻子", "sdhsadjashl"));
            sqlSession.commit();
            if (i > 0)
                System.out.println("插入成功");
        } catch (Exception e) {

        } finally {
            // 关闭sqlSession
            sqlSession.close();
        }
    }
}
