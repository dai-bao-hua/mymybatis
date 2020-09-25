package com.zking.sqlsession;

import com.sun.org.apache.bcel.internal.generic.Select;
import com.zking.pojo.Configuration;
import com.zking.pojo.MapperdSTatement;

import java.util.List;

/**
 * @version 1.0
 * @author：戴宝华
 * @copyright: 阿里巴巴
 * @Date: 2020/9/24 13:38
 **/
public class DefaultSqlSession implements Sqlsession {


    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration){
        this.configuration=configuration;
    }


    public <E> List<E> selectList(String StatementId, Object... params) {
        //将要完成对SimpleExecutor里的query方法的调用

        SimpleExecutor simpleExecutor = new SimpleExecutor();


        MapperdSTatement mapperdSTatement = configuration.getMapperdSTatementMap().get(StatementId);

        List<Object> querey = null;
        try {
            querey = simpleExecutor.querey(configuration, mapperdSTatement, params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (List<E>) querey;
    }

    public <T> T selectOne(String StatementId, Object... params) {

        List<Object> objects = this.selectList(StatementId, params);


        if(1==objects.size()){
            return  (T)objects.get(0);
        }else{
         throw  new RuntimeException("查询结果为空，或者返回结果太多");
        }


    }
}
