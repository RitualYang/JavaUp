package com.frame.dao;

import com.frame.domain.User;
import com.frame.mybatis.annotations.Select;

import java.util.List;

/**
 * @author WTY
 * @Date 2020/5/17 18:16
 *
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from user")
    List<User> findAll();
}
