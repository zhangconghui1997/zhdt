package com.bf.dt.util;

import java.util.UUID;

public class FileUtil {

    public static String renameFile(String fileName){
        if(fileName.length()>50){
            fileName=fileName.substring(fileName.length()-50);
        }
        return System.currentTimeMillis()+"_"+ UUID.randomUUID().toString().replace("-","")+"_"+fileName;
    }
}
