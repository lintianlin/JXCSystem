package com.sinfeeloo.invoicing.basicinfo.pojo;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/29 15:16
 */
public class SelectItem {
    private String id;
    private String name;

    public SelectItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
