package com.frame.dao;

import com.frame.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 符合SpringDataJpa的dao层接口规范
 *      JpaRepository<操作的实体类类型，实体类中主键属性的类型>
 *          * 封装了基本CRUD操作
 *      JpaSpecificationExecutor<操作的实体类类型>
 *          * 封装了复杂查询（分页）
 */
public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    /**
     * 案例：根据用户名称查询用户
     *      使用jpql的形式查询
     *  jpql：from User where username = ?
     *
     *  配置jpql语句，使用的@Query注解
     */
    @Query(value="from User where username = ?1")
    User findJpql(String username);


    /**
     * 案例：根据用户名称和用户id查询用户
     *      jpql： from User where username = ? and id = ?
     *
     *  对于多个占位符参数
     *      赋值的时候，默认的情况下，占位符的位置需要和方法参数中的位置保持一致
     *
     *  可以指定占位符参数的位置
     *      ? 索引的方式，指定此占位的取值来源
     */
    @Query(value = "from User where username = ?2 and id = ?1")
    User findusernameAndId(Integer id,String name);

    /**
     * 使用jpql完成更新操作
     *      案例 ： 根据id更新，用户的名称
     *          更新4号用户的名称，将名称改为“狠人大地”
     *
     *  sql  ：update user set username = ? where id = ?
     *  jpql : update User set username = ? where id = ?
     *
     *  @Query : 代表的是进行查询
     *      * 声明此方法是用来进行更新操作
     *  @Modifying
     *      * 当前执行的是一个更新操作
     *
     */
    @Query(value = " update User set username = ?2 where id = ?1 ")
    @Modifying
    void updateUser(Integer id,String username);


    /**
     * 使用sql的形式查询：
     *     查询全部的用户
     *  sql ： select * from user;
     *  Query : 配置sql查询
     *      value ： sql语句
     *      nativeQuery ： 查询方式
     *          true ： sql查询
     *          false：jpql查询
     *
     */
    //@Query(value = " select * from user" ,nativeQuery = true)
    @Query(value="select * from user where username like ?1",nativeQuery = true)
    List<Object [] > findSql(String name);


    /**
     * 方法名的约定：
     *      findBy : 查询
     *            对象中的属性名（首字母大写） ： 查询的条件
     *            username
     *            * 默认情况 ： 使用 等于的方式查询
     *                  特殊的查询方式
     *
     *  findByusername   --   根据用户名称查询
     *
     *  再springdataJpa的运行阶段
     *          会根据方法名称进行解析  findBy    from  xxx(实体类)
     *                                      属性名称      where  username =
     *
     *      1.findBy  + 属性名称 （根据属性名称进行完成匹配的查询=）
     *      2.findBy  + 属性名称 + “查询方式（Like | isnull）”
     *          findByusernameLike
     *      3.多条件查询
     *          findBy + 属性名 + “查询方式”   + “多条件的连接符（and|or）”  + 属性名 + “查询方式”
     */
    User findByUsername(String username);

    List<User> findByUsernameLike(String username);
}