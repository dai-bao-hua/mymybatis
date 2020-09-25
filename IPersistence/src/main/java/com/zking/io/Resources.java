package com.zking.io;

import java.io.InputStream;

/**
 * @author：戴宝华
 * @copyright: 阿里巴巴
 **/
public class Resources {


    //根据配置文件的路径，将配置文件加载成字节输入流，存储到内存中
    public static InputStream getResourceAsStream(String path){

        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
