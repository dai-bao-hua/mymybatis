package com.zking.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zking.io.Resources;
import com.zking.pojo.Configuration;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author：戴宝华
 * @copyright: 阿里巴巴
 *
 *
 **/
public class XmlConfigBuilder {
    /*
    * 该方法就是将配置文件，封装解析的内容到Configuration中
    * */


    private Configuration configuration;

    public XmlConfigBuilder() {
        this.configuration=new Configuration();
    }

    public Configuration parseConfig(InputStream inputStream) throws Exception {
        Document document = new SAXReader().read(inputStream);

        //<configuration>
        Element rootElement = document.getRootElement();



        List<Element> list = rootElement.selectNodes("//property");

        Properties properties=new Properties();//存储配置信息
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));


        configuration.setDataSource(comboPooledDataSource);



        //对mapper.xml的解析，拿到路径--字节输入流--dom4j解析

        List<Element> list1 = rootElement.selectNodes("//mapper");
        for (Element element :list1){
            String mapperPath=element.attributeValue("resource");
            //调用Resources的根据路径获得字节输入流的方法。
            InputStream resourceAsStream = Resources.getResourceAsStream(mapperPath);


            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsStream);

        }

        return configuration;
    }




}
