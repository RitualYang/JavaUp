package com.frame.mybatis.sqlsession;

/**
 * @author WTY
 * @Date 2020/5/17 18:16
 */
public interface SqlSessionFactory {

    /**
     * 用于打开一个新的SqlSession对象
     * @return
     */
    SqlSession openSession();
}
