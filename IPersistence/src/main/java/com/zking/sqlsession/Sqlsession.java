package com.zking.sqlsession;

import java.util.List;

/**
 * @version 1.0
 * @author：戴宝华
 * @copyright: 阿里巴巴
 * @Date: 2020/9/24 13:36
 **/
public interface Sqlsession {
    //查询所有
    public <E> List<E> selectList(String StatementId,Object...params);

    //查询单个
    public <T> T selectOne(String StatementId,Object...params);


}
