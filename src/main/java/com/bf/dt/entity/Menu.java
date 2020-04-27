package com.bf.dt.entity;

public class Menu {
    private String uuid;

    private String menuName;

    private String path;

    private String parentid;


    private String vuecomp;


    private String vuename;

    public String getVuecomp() {
        return vuecomp;
    }

    public void setVuecomp(String vuecomp) {
        this.vuecomp = vuecomp;
    }

    public String getVuename() {
        return vuename;
    }

    public void setVuename(String vuename) {
        this.vuename = vuename;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }
}