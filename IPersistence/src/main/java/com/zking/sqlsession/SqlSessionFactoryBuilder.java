package com.zking.sqlsession;

import com.zking.config.XmlConfigBuilder;
import com.zking.pojo.Configuration;
import org.dom4j.DocumentException;

import java.io.InputStream;

/**
 * @author：戴宝华
 * @copyright: 阿里巴巴
 **/
public class SqlSessionFactoryBuilder {
    public SqlSessionfactory  build(InputStream in) throws Exception {
        //1.使用dom4j解析配置文件，将解析出来的内容封装到Configuration中
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);


        //2.创建sqlSessionFactory对象:工厂类，主要是用来生产sqlsession（会话对象）
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);



        return defaultSqlSessionFactory;
    }



}
