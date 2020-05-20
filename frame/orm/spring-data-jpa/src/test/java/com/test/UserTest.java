package com.test;

import com.frame.dao.UserDao;
import com.frame.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class UserTest {
    @Autowired
    private UserDao userDao;

    /**
     * 根据id查询
     */
    @Test
    public void testFindOne() {
        User user = userDao.findOne(41);
        System.out.println(user);
    }

    /**
     * save : 保存或者更新
     *      根据传递的对象是否存在主键id，
     *      如果没有id主键属性：保存
     *      存在id主键属性，根据id查询数据，更新数据
     */
    @Test
    public void testSave() {
        User user  = new User();
        user.setUsername("狠人大地");
        user.setSex("男");
        user.setAddress("天下");
        user.setBirthday(new Date());
        userDao.save(user);
    }

    @Test
    public void testUpdate() {
        User user  = new User();
        user.setId(41);
        user.setUsername("狠人大地很厉害");
        userDao.save(user);
    }

    @Test
    public void testDelete () {
        userDao.delete(42);
    }


    /**
     * 查询所有
     */
    @Test
    public void testFindAll() {
        List<User> list = userDao.findAll();
        for(User user : list) {
            System.out.println(user);
        }
    }

    /**
     * 测试统计查询：查询用户的总数量
     *      count:统计总条数
     */
    @Test
    public void testCount() {
        long count = userDao.count();//查询全部的用户数量
        System.out.println(count);
    }

    /**
     * 测试：判断id为4的用户是否存在
     *      1. 可以查询以下id为4的用户
     *          如果值为空，代表不存在，如果不为空，代表存在
     *      2. 判断数据库中id为4的用户的数量
     *          如果数量为0，代表不存在，如果大于0，代表存在
     */
    @Test
    public void  testExists() {
        boolean exists = userDao.exists(44);
        System.out.println("id为4的用户 是否存在："+exists);
    }


    /**
     * 根据id从数据库查询
     *      @Transactional : 保证getOne正常运行
     *
     *  findOne：
     *      em.find()           :立即加载
     *  getOne：
     *      em.getReference     :延迟加载
     *      * 返回的是一个用户的动态代理对象
     *      * 什么时候用，什么时候查询
     */
    @Test
    @Transactional
    public void  testGetOne() {
        User user = userDao.getOne(44);
        System.out.println(user);
    }

}
