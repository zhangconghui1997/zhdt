package com.bf.dt.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class C001PO implements Serializable {

    private static final long serialVersionUID = -5389033373347000282L;
    public static final String MA017_VALID = "1";
    public static final String MA017_DEL = "0";
    public static final String MA008_VALID = "10";
    public static final String MA008_NO = "00";
    private String MA001;
    private String MA002;
    private String MA003;
    private String MA004;
    private String MA005;
    private String MA006;
    private String MA007;
    private String MA008;
    private Integer MA009;
    private String MA010;
    private String MA011;
    private Date MA012;
    private String MA013;
    private Date MA014;
    private String MA015;
    private String MA016;
    private String MA017;
    private String MA018;
    private String MA019;
    private String MA020;
    private String MA021;
    private String[] c004ids;


    public C001PO() {
    }

    public boolean isValid() {
        return "10".equals(this.MA008);
    }

    public String getMA001() {
        return this.MA001;
    }

    public void setMA001(String mA001) {
        this.MA001 = mA001;
    }

    public String getMA002() {
        return this.MA002;
    }

    public void setMA002(String mA002) {
        this.MA002 = mA002;
    }

    public String getMA003() {
        return this.MA003;
    }

    public void setMA003(String mA003) {
        this.MA003 = mA003;
    }

    public String getMA004() {
        return this.MA004;
    }

    public void setMA004(String mA004) {
        this.MA004 = mA004;
    }

    public String getMA005() {
        return this.MA005;
    }

    public void setMA005(String mA005) {
        this.MA005 = mA005;
    }

    public String getMA006() {
        return this.MA006;
    }

    public void setMA006(String mA006) {
        this.MA006 = mA006;
    }

    public String getMA007() {
        return this.MA007;
    }

    public void setMA007(String mA007) {
        this.MA007 = mA007;
    }

    public String getMA008() {
        return this.MA008;
    }

    public void setMA008(String mA008) {
        this.MA008 = mA008;
    }

    public Integer getMA009() {
        return this.MA009;
    }

    public void setMA009(Integer mA009) {
        this.MA009 = mA009;
    }

    public String getMA010() {
        return this.MA010;
    }

    public void setMA010(String mA010) {
        this.MA010 = mA010;
    }

    public String getMA011() {
        return this.MA011;
    }

    public void setMA011(String mA011) {
        this.MA011 = mA011;
    }

    public Date getMA012() {
        return this.MA012;
    }

    public void setMA012(Date mA012) {
        this.MA012 = mA012;
    }

    public String getMA013() {
        return this.MA013;
    }

    public void setMA013(String mA013) {
        this.MA013 = mA013;
    }

    public Date getMA014() {
        return this.MA014;
    }

    public void setMA014(Date mA014) {
        this.MA014 = mA014;
    }

    public String getMA015() {
        return this.MA015;
    }

    public void setMA015(String mA015) {
        this.MA015 = mA015;
    }

    public String getMA016() {
        return this.MA016;
    }

    public void setMA016(String mA016) {
        this.MA016 = mA016;
    }

    public String getMA017() {
        return this.MA017;
    }

    public void setMA017(String mA017) {
        this.MA017 = mA017;
    }

    public String getMA018() {
        return this.MA018;
    }

    public void setMA018(String mA018) {
        this.MA018 = mA018;
    }

    public String getMA019() {
        return this.MA019;
    }

    public void setMA019(String mA019) {
        this.MA019 = mA019;
    }

    public String getMA020() {
        return this.MA020;
    }

    public void setMA020(String mA020) {
        this.MA020 = mA020;
    }

    public String getMA021() {
        return this.MA021;
    }

    public void setMA021(String mA021) {
        this.MA021 = mA021;
    }

    public String[] getC004ids() {
        return this.c004ids;
    }

    public void setC004ids(String[] c004ids) {
        this.c004ids = c004ids;
    }


}
