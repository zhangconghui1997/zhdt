package com.bf.dt.util;

import java.util.Random;
import java.util.UUID;

public class MakeCode {

    public static String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++)
                fourRandom = "0" + fourRandom ;
        }
        return fourRandom;
    }

    public static String getUUID() {
        String str = UUID.randomUUID().toString();
        str = str.replaceAll("-", "");
        return str;
    }





    public static void main(String[] args) {
        /*String fourRandom = getFourRandom();
        System.out.println(fourRandom);*/
        String uuid = getUUID();
        System.out.println(uuid);
    }

}
