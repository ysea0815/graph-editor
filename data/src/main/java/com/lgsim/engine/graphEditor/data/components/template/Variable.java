package com.lgsim.engine.graphEditor.data.components.template;

/**
 * 参数变量类
 */
public class Variable {
    private String name;//变量名
    private double value;//变量值
    private String description;//变量描述


    public Variable() {
        this.name = "";
        this.value = 0;
        this.description = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
