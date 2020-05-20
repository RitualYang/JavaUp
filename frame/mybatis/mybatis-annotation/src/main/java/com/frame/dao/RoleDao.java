package com.frame.dao;

import com.frame.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author WTY
 */
public interface RoleDao {

    /**
     * 查询所有角色
     * @return
     */
    @Select("select * from role")
    @Results(id="roleMap",value = {
            @Result(id=true,column = "rid",property = "roleId"),
            @Result(column = "role_name",property = "roleName"),
            @Result(column = "role_desc",property = "roleDesc"),
            @Result(property = "users",column = "uid",
                    many=@Many(select="com.frame.dao.UserDao.findById",fetchType= FetchType.EAGER))
    })
    List<Role> findAll();

    /**
     * 根据uid查询所有角色
     * @param uid
     * @return
     */
    @Select("select * from role where id in(select rid from user_role where uid = #{userId}) ")
    List<Role> findRoleByUid(Integer uid);


}