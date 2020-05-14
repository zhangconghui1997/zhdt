package com.bf.dt.entity;

public class OneNetDsPO {

    /**
     * 传感器类型标识
     */
    private String ma001;

    /**
     * 数据流ID
     */
    private String ma003;

    /**
     * 数据流名称
     */
    private String ma004;

    /**
     * 数据流单位
     * @return
     */
    private String ma005;

    /**
     * 备用字段
     */
    private String ma002;
    // -------------- getter and setter --------------

    public String getMa001() {
        return ma001;
    }

    public void setMa001(String ma001) {
        this.ma001 = ma001;
    }

    public String getMa002() {
        return ma002;
    }

    public void setMa002(String ma002) {
        this.ma002 = ma002;
    }

    public String getMa003() {
        return ma003;
    }

    public void setMa003(String ma003) {
        this.ma003 = ma003;
    }

    public String getMa004() {
        return ma004;
    }

    public void setMa004(String ma004) {
        this.ma004 = ma004;
    }

    public String getMa005() {
        return ma005;
    }

    public void setMa005(String ma005) {
        this.ma005 = ma005;
    }
}
