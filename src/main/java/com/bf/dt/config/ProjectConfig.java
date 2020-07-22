package com.bf.dt.config;

public class ProjectConfig {

//reids
    public static final String RIP = "localhost";
    public static final int RPEOT = 6379;
    public static final String RPWD = "123456";



//用户
    //用户密码错误次数
    public static final String PCOUNT = "pcount";
    //用户jwt令牌有效期
    public static final String JWT = "jwt";

    public static final String USERSD="userfreeze:";// 所有冻结的用户

    //jwt
    public static final String JWTKEY= "zhdt";
    public static final int JWTTIMET = 30;
    public static final String TOKENHEADER = "usertoken";


}
