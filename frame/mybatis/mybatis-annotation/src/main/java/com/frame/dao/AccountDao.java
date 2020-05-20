package com.frame.dao;

import com.frame.domain.Account;
import com.frame.domain.AccountUser;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author WTY
 */
public interface AccountDao {

    /**
     * 查询所有账户，同时还要获取到当前账户的所属用户信息
     * @return
     */
    @Select("select * from account")
    @Results(id="accountMap",value = {
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            @Result(property = "user",column = "uid",one=@One(select="com.frame.dao.UserDao.findById",fetchType= FetchType.EAGER))
    })
    List<Account> findAll();

    /**
     * 查询所有账户，并且带有用户名称和地址信息
     * @return
     */
    @Select("select a.*,u.username,u.address from account a , user u where u.id = a.uid")
    List<AccountUser> findAllAccount();

    /**
     * 根据uid查询用户信息
     * @param uid 用户id
     * @return
     */
    @Select("select * from account where uid = #{userId}")
    List<Account> findAccountByUid(Integer uid);
}
