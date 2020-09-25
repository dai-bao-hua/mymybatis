package com.zking.sqlsession;

import com.zking.pojo.Configuration;

/**
 * @version 1.0
 * @author：戴宝华
 * @copyright: 阿里巴巴
 * @Date: 2020/9/24 13:20
 **/
public class DefaultSqlSessionFactory implements  SqlSessionfactory{

    private Configuration configuration;
    public DefaultSqlSessionFactory(Configuration configuration) {
       this.configuration=configuration;
    }


    public Sqlsession openSession() {
        return new DefaultSqlSession(configuration);
    }
}




