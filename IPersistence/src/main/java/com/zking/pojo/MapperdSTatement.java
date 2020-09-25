package com.zking.pojo;

/**
 * @author：戴宝华
 * @copyright: 阿里巴巴
 **/
public class MapperdSTatement {

    // id标识
    private String id;
    //返回值   类型
    private String restultType;
    //参数值类型
    private String paramterType;
    //sql语句
    private  String sql;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestultType() {
        return restultType;
    }

    public void setRestultType(String restultType) {
        this.restultType = restultType;
    }

    public String getParamterType() {
        return paramterType;
    }

    public void setParamterType(String paramterType) {
        this.paramterType = paramterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
