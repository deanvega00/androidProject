package com.Lenovo.phclapps.Adapter;

import java.io.Serializable;


public class LawModel implements Serializable{
    String title;
    String desc;
    int lawId;

    public int getLawId() {
        return lawId;
    }

    public void setLawId(int lawId) {
        this.lawId = lawId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    boolean isfavt;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isIsfavt() {
        return isfavt;
    }

    public void setIsfavt(boolean isfavt) {
        this.isfavt = isfavt;
    }

    String id;

    //constructor
    public LawModel(String title, String desc, String id) {
        this.title = title;
        this.desc = desc;
        this.id = id;
    }

    //getters


    public String getTitle() {
        return this.title;
    }

    public String getDesc() {
        return this.desc;
    }


}
