package com.zking.util;


import lombok.Data;

import java.io.Serializable;


@Data
public class ParameterMapping implements Serializable {

    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ParameterMapping(String content) {
        this.content = content;
    }

}
