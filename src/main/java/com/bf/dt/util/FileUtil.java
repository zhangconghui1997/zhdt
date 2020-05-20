package com.bf.dt.util;

import java.util.UUID;

public class FileUtil {
    public static final String dDesc = "f:/drug/";
    public static final String dImg = "src/main/resources/static/img/drug";

    public static String renameFile(String fileName){
        if(fileName.length()>50){
            fileName=fileName.substring(fileName.length()-50);
        }
        return System.currentTimeMillis()+"_"+ UUID.randomUUID().toString().replace("-","")+"_"+fileName;
    }
}
