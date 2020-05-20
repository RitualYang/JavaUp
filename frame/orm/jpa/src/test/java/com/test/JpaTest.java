package com.test;

import com.frame.domain.User;
import com.frame.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * @author WTY
 * @Date 2020/5/19 16:26
 */
public class JpaTest {

    /**
     * 测试jpa的保存
     *      案例：保存一个用户到数据库中
     *  Jpa的操作步骤
     *     1.加载配置文件创建工厂（实体管理器工厂）对象
     *     2.通过实体管理器工厂获取实体管理器
     *     3.获取事务对象，开启事务
     *     4.完成增删改查操作
     *     5.提交事务（回滚事务）
     *     6.释放资源
     */
    @Test
    public void testSave(){
        //1.加载配置文件创建工厂（实体管理器工厂）对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过实体管理器工厂获取实体管理器
        EntityManager em = factory.createEntityManager();
        //3.获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction(); //获取事务对象
        tx.begin();//开启事务
        //4.完成增删改查操作：保存一个用户到数据库中
        User user = new User();
        user.setUsername("朝天骄");
        user.setAddress("北京市顺义区");
        user.setSex("男");
        user.setBirthday(new Date());
        //保存，
        em.persist(user); //保存操作
        //5.提交事务
        tx.commit();
        //6.释放资源
        em.close();
        factory.close();
    }

    /**
     * 删除用户的案例
     *
     */
    @Test
    public  void testRemove() {
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.增删改查 -- 删除用户

        //i 根据id查询用户
        User user = entityManager.find(User.class,41l);
        //ii 调用remove方法完成删除操作
        entityManager.remove(user);

        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }


    /**
     * 更新用户的操作
     *      merge(Object)
     */
    @Test
    public  void testUpdate() {
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.增删改查 -- 更新操作

        //i 查询用户
        User user = entityManager.find(User.class,42);
        //ii 更新用户
        user.setSex("男");
        entityManager.merge(user);

        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 根据id查询用户
     *  使用find方法查询：
     *      1.查询的对象就是当前用户对象本身
     *      2.在调用find方法的时候，就会发送sql语句查询数据库
     *
     *  立即加载
     */
    @Test
    public  void testFind() {
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.增删改查 -- 根据id查询用户
        /**
         * find : 根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         *      id：查询的主键的取值
         */
        User user = entityManager.find(User.class, 41);
        System.out.print(user);
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }
    /**
     * 根据id查询用户
     *      getReference方法
     *          1.获取的对象是一个动态代理对象
     *          2.调用getReference方法不会立即发送sql语句查询数据库
     *              * 当调用查询结果对象的时候，才会发送查询的sql语句：什么时候用，什么时候发送sql语句查询数据库
     *
     * 延迟加载（懒加载）
     *      * 得到的是一个动态代理对象
     *      * 什么时候用，什么使用才会查询
     */

    @Test
    public  void testReference() {
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.增删改查 -- 根据id查询用户
        /**
         * getReference : 根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         *      id：查询的主键的取值
         */
        User user = entityManager.getReference(User.class, 41);
        System.out.print(user);
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }
}
