package com.frame.dao;

import com.frame.domain.QueryVo;
import com.frame.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author WTY
 * @Date 2020/5/17 17:54
 */
// 开启二级缓存
@CacheNamespace(blocking = true)
public interface UserDao {
    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    @Results(id="userMap",value={
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "address",property = "address"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "birthday",property = "birthday"),
            @Result(property = "accounts",column = "id",
                    many = @Many(select = "com.frame.dao.AccountDao.findAccountByUid",
                            fetchType = FetchType.LAZY)),
            @Result(property = "roles",column = "id",
                    many = @Many(select = "com.frame.dao.RoleDao.findRoleByUid",
                            fetchType = FetchType.LAZY))
    })
    List<User> findAll();
    /**
     * 保存用户
     * @param user
     */
    @Insert("insert into user(username,address,sex,birthday)values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update user set username=#{username},sex=#{sex},birthday=#{birthday},address=#{address} where id=#{id}")
    void updateUser(User user);

    /**
     * 根据Id删除用户
     * @param userId
     */
    @Delete("delete from user where id=#{id} ")
    void deleteUser(Integer userId);

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    @Select("select * from user where id=#{id}")
    @ResultMap("userMap")
    User findById(Integer userId);

    /**
     * 根据名称模糊查询用户信息
     * @param username
     * @return
     */
    //@Select("select * from user where username like #{username}")
    @Select("select * from user where username like '%${value}%' ")
    @ResultMap("userMap")
    List<User> findByName(String username);

    /**
     * 查询总用户数
     * @return
     */
    @Select("select count(*) from user")
    int findTotal();

    /**
     * 根据queryVo中的条件查询用户
     * @param vo
     * @return
     */
    @Select("select * from user where username like #{user.username}")
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入参数条件
     * @param user 查询的条件：有可能有用户名，有可能有性别，也有可能有地址，还有可能是都有
     * @return
     */
    //List<User> findUserByCondition(User user);

    /**
     * 根据queryvo中提供的id集合，查询用户信息
     * @param vo
     * @return
     */
    //List<User> findUserInIds(QueryVo vo);

    /**
     * 查询所有用户，同时获取到用户下所有账户的信息
     * @return
     */
    //List<User> findAllUserAccount();

    /**
     * 查询所有用户，同时获取到用户所有角色信息
     * @return
     */
    //List<User> findAllUserRole();
}
