package com.example.sidebar;

/**
 * Created by little on 2017/4/20 0020.
 */

public class DataBean {
    private String name;
    private int type;

    public DataBean(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
