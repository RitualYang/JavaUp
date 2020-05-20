package com.frame.mybatis.sqlsession;

import com.frame.mybatis.cfg.Configuration;
import com.frame.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.frame.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @author WTY
 * @Date 2020/5/17 18:16
 *  用于创建一个SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据参数的字节输入流来构建一个SqlSessionFactory工厂
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return  new DefaultSqlSessionFactory(cfg);
    }
}
