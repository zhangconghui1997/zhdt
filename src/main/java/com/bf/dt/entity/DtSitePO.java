package com.bf.dt.entity;

import java.io.Serializable;
import java.util.List;

public class DtSitePO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String ma001;
    private String ma002;//园区标识
    private String ma003;//区域名称
    private String ma004;//区域编号
    private String ma005;//场地类型标识
    private String ma006;//场地面积
    private String ma007;//场地状态标识
    private String ma008;//种植方式标识
    private String ma009;//责任人标识
    private String ma010;//种植品种标识
    private String ma011;//关联摄像头标识
    private String ma012;//用于  0：normal  1:weather 2:流量计
    private String ma013;//备用

    //
    private String ma0101; // 种植品种
    private String ma0051; // 场地类型
    private String ma0071; // 场地状态
    private String ma0081; // 种植方式
    private String ma0091; // 责任人

    private String oilhumi;
    private String oiltemp;

    private String yuanQuName;//园区名称

    private int dev;//Onenet设备数量
    private int ca;//摄像头数量
    private int devNum;//设备总数量





    public String getYuanQuName() {
        return yuanQuName;
    }

    public void setYuanQuName(String yuanQuName) {
        this.yuanQuName = yuanQuName;
    }

    public String getOilhumi() {
        return oilhumi;
    }

    public void setOilhumi(String oilhumi) {
        this.oilhumi = oilhumi;
    }

    public String getOiltemp() {
        return oiltemp;
    }

    public void setOiltemp(String oiltemp) {
        this.oiltemp = oiltemp;
    }

    public String getMa0051() {
        return ma0051;
    }

    public void setMa0051(String ma0051) {
        this.ma0051 = ma0051;
    }

    public String getMa0071() {
        return ma0071;
    }

    public void setMa0071(String ma0071) {
        this.ma0071 = ma0071;
    }

    public String getMa0081() {
        return ma0081;
    }

    public void setMa0081(String ma0081) {
        this.ma0081 = ma0081;
    }

    public String getMa0091() {
        return ma0091;
    }

    public void setMa0091(String ma0091) {
        this.ma0091 = ma0091;
    }

    public String getMa0101() {
        return ma0101;
    }

    public void setMa0101(String ma0101) {
        this.ma0101 = ma0101;
    }

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

    public String getMa006() {
        return ma006;
    }

    public void setMa006(String ma006) {
        this.ma006 = ma006;
    }

    public String getMa007() {
        return ma007;
    }

    public void setMa007(String ma007) {
        this.ma007 = ma007;
    }

    public String getMa008() {
        return ma008;
    }

    public void setMa008(String ma008) {
        this.ma008 = ma008;
    }

    public String getMa009() {
        return ma009;
    }

    public void setMa009(String ma009) {
        this.ma009 = ma009;
    }

    public String getMa010() {
        return ma010;
    }

    public void setMa010(String ma010) {
        this.ma010 = ma010;
    }

    public String getMa011() {
        return ma011;
    }

    public void setMa011(String ma011) {
        this.ma011 = ma011;
    }

    public String getMa012() {
        return ma012;
    }

    public void setMa012(String ma012) {
        this.ma012 = ma012;
    }

    public String getMa013() {
        return ma013;
    }

    public void setMa013(String ma013) {
        this.ma013 = ma013;
    }

    public int getDev() {
        return dev;
    }

    public void setDev(int dev) {
        this.dev = dev;
    }

    public int getCa() {
        return ca;
    }

    public void setCa(int ca) {
        this.ca = ca;
    }

    public int getDevNum() {
        return dev + ca;
    }

    public void setDevNum(int devNum) {
        this.devNum = devNum;
    }

}
