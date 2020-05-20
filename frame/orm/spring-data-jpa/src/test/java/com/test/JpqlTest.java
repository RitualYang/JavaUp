package com.test;

import com.frame.dao.UserDao;
import com.frame.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class JpqlTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void  testFindJPQL() {
        User user = userDao.findJpql("天下");
        System.out.println(user);
    }


    @Test
    public void testFindusernameAndId() {
        User user =  userDao.findusernameAndId(45,"天下");
        System.out.println(user);
    }

    /**
     * 测试jpql的更新操作
     *  * springDataJpa中使用jpql完成 更新/删除操作
     *         * 需要手动添加事务的支持
     *         * 默认会执行结束之后，回滚事务
     *   @Rollback : 设置是否自动回滚
     *          false | true
     */
    @Test
    @Transactional //添加事务的支持
    @Rollback(value = false)
    public void testUpdateUser() {
        userDao.updateUser(44,"狠人大地");
    }

    //测试sql查询
    @Test
    public void testFindSql() {
        List<Object[]> list = userDao.findSql("天下%");
        for(Object [] obj : list) {
            System.out.println(Arrays.toString(obj));
        }
    }


    //测试方法命名规则的查询
    @Test
    public void testNaming() {
        User user = userDao.findByUsername("天下");
        System.out.println(user);
    }


    //测试方法命名规则的查询
    @Test
    public void testFindByusernameLike() {
        List<User> list = userDao.findByUsernameLike("天下%");
        for (User user : list) {
            System.out.println(user);
        }
    }
}
