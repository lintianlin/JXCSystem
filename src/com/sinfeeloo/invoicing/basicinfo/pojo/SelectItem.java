package com.sinfeeloo.invoicing.basicinfo.pojo;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/29 15:16
 */
public class SelectItem {
    private int id;
    private String name;

    public SelectItem() {
    }

    public SelectItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return getName();
    }
}
