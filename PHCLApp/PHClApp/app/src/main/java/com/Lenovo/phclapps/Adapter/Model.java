package com.Lenovo.phclapps.Adapter;



public class Model {
    String title;
    String desc;

    String icon;

    //constructor
    public Model(String title, String desc, String icon) {
        this.title = title;
        this.desc = desc;
        this.icon = icon;
    }

    //getters


    public String getTitle() {
        return this.title;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
