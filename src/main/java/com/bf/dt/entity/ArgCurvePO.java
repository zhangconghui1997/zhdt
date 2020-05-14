package com.bf.dt.entity;

public class ArgCurvePO {
    /**
     * 数据流Id
     */
    private String DSID;

    /**
     * 数据流值
     */
    private String DSVALUE;

    /**
     * 时间
     */
    private String VALUETIME;

    public String getDSID() {
        return DSID;
    }

    public void setDSID(String DSID) {
        this.DSID = DSID;
    }

    public String getDSVALUE() {
        return DSVALUE;
    }

    public void setDSVALUE(String DSVALUE) {
        this.DSVALUE = DSVALUE;
    }

    public String getVALUETIME() {
        return VALUETIME;
    }

    public void setVALUETIME(String VALUETIME) {
        this.VALUETIME = VALUETIME;
    }

}
