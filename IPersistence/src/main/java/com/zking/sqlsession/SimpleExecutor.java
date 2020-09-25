package com.zking.sqlsession;

import com.zking.config.BoundSql;
import com.zking.pojo.Configuration;
import com.zking.pojo.MapperdSTatement;
import com.zking.util.GenericTokenParser;
import com.zking.util.ParameterMapping;
import com.zking.util.ParameterMappingTokenHandler;
import com.zking.util.TokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @version 1.0
 * @author：戴宝华
 * @copyright: 阿里巴巴
 * @Date: 2020/9/24 18:47
 **/
public class SimpleExecutor implements Executor{
    public <E> List<E> querey(Configuration configuration, MapperdSTatement mapperdSTatement, Object... params) throws Exception {
        //1.注册驱动，获得链接
        Connection connection = configuration.getDataSource().getConnection();


        //2.获取sql语句  select *from user where id=#{id} and username=#{username}

        String sql = mapperdSTatement.getSql();

        //3.转换Sql语句为 select *from user where id=？ and username=？，还需要对#{}里面的值进行解析存储
        BoundSql boundSql=getBoudSql(sql);


        //4.获取预处理对象：PreParedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        //5.设置sql参数
            //获取参数类型的全路径
        String paramterType = mapperdSTatement.getParamterType();
        Class<?> paramterTypeClass=getClassType(paramterType);

        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();

            //设置反射，根据参数名称来获取实体对象中的属性对象值
            Field declaredField = paramterTypeClass.getDeclaredField(content);
            //因为实体类的属性一般都会封装，所有属性一般是私有的。所以设置暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);

            preparedStatement.setObject(i+1,o);
        }



        //6.执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        //7.封装返回结果集
        //返回结果的权限列名
        String restultType = mapperdSTatement.getRestultType();
        Class<?> resultTypeClass = getClassType(restultType);



        ArrayList<Object> objects = new ArrayList<>();

        //元数据
        ResultSetMetaData metaData = resultSet.getMetaData();
        while(resultSet.next()){
            Object o = resultTypeClass.getDeclaredConstructor().newInstance();
            for (int i = 1; i <=metaData.getColumnCount() ; i++) {
                //字段名
                String columnName = metaData.getColumnName(i);

                //根据字段名，获得字段的值
                Object value = resultSet.getObject(columnName);


                //使用反射或者内省，根据数据库表和实体的对应关系完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o,value);


            }

            objects.add(o);//把封装好的实体对象存进List中
        }

        return (List<E>) objects;
    }

    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if(null!=paramterType&&""!=paramterType){
            Class<?> aClass = Class.forName(paramterType);
            return aClass;
        }
        return null;

    }

    /**
     * 完成对#{}的解析工作：1.将#{}用？代替  2.解析出#{}里面的值进行存储
     * @param sql
     * @return
     */
    private BoundSql getBoudSql(String sql) {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{","}",parameterMappingTokenHandler);

        //解析出来的sql
        String parseSql = genericTokenParser.parse(sql);
        //#{}解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();


        BoundSql boundSql = new BoundSql(parseSql,parameterMappings);


        return boundSql;
    }
}
