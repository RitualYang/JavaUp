package com.frame.dao;

import com.frame.domain.Role;

import java.util.List;

/**
 * @author WTY
 */
public interface RoleDao {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();
}