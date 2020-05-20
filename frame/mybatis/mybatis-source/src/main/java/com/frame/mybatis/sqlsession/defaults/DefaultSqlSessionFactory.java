package com.frame.mybatis.sqlsession.defaults;

import com.frame.mybatis.cfg.Configuration;
import com.frame.mybatis.sqlsession.SqlSession;
import com.frame.mybatis.sqlsession.SqlSessionFactory;


/**
 * @author WTY
 * @Date 2020/5/17 18:16
 * SqlSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }

    /**
     * 用于创建一个新的操作数据库对象
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
