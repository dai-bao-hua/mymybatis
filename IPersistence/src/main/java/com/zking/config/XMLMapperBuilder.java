package com.zking.config;

import com.zking.pojo.Configuration;
import com.zking.pojo.MapperdSTatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @version 1.0
 * @author：戴宝华
 * @copyright: 阿里巴巴
 * @Date: 2020/9/24 12:53
 **/
public class XMLMapperBuilder {
    private  Configuration configuration;
    public XMLMapperBuilder(Configuration configuration) {
        this.configuration=configuration;
    }



    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");


        List<Element> list = rootElement.selectNodes("//select");
        for (Element element : list) {
            String id=  element.attributeValue("id");//id
            String restultType=   element.attributeValue("restultType");//返回值
            String paramterType=   element.attributeValue("paramterType");//参数
            String sqlText = element.getTextTrim();


            MapperdSTatement mapperdSTatement = new MapperdSTatement();

            mapperdSTatement.setId(id);
            mapperdSTatement.setRestultType(restultType);
            mapperdSTatement.setParamterType(paramterType);
            mapperdSTatement.setSql(sqlText);


            String key= namespace+"."+id;//namesspce.id

            configuration.getMapperdSTatementMap().put(key,mapperdSTatement);
        }

    }
}
