package com.zking.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author：戴宝华
 * @copyright: 阿里巴巴
 **/
public class Configuration {

    private DataSource dataSource;
    /*
    * key statementid   value:封装好的mappedStatement对象
    * */
    Map<String,MapperdSTatement> mapperdSTatementMap=new HashMap<String, MapperdSTatement>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MapperdSTatement> getMapperdSTatementMap() {
        return mapperdSTatementMap;
    }

    public void setMapperdSTatementMap(Map<String, MapperdSTatement> mapperdSTatementMap) {
        this.mapperdSTatementMap = mapperdSTatementMap;
    }
}
