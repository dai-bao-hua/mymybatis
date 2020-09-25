package com.zking.config;

import com.zking.util.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author：戴宝华
 * @copyright: 阿里巴巴
 * @Date: 2020/9/24 19:11
 **/
public class BoundSql {
    private String sqlText;//解析过后的sql

    private List<ParameterMapping> ParameterMappingList=new ArrayList<>();


    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        ParameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return ParameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        ParameterMappingList = parameterMappingList;
    }
}
