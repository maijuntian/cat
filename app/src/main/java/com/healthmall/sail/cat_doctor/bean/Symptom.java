package com.healthmall.sail.cat_doctor.bean;

/**
 * Created by mai on 2018/1/24.
 */
public class Symptom {

    public Symptom(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * name : 正常
     * description : 正常
     */

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
