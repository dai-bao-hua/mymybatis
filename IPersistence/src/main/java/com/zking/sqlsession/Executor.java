package com.zking.sqlsession;

import com.zking.pojo.Configuration;
import com.zking.pojo.MapperdSTatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @version 1.0
 * @author：戴宝华
 * @copyright: 阿里巴巴
 * @Date: 2020/9/24 18:43
 **/
public interface Executor {


    public <E> List<E> querey(Configuration configuration, MapperdSTatement mapperdSTatement,Object... params) throws Exception;


}
