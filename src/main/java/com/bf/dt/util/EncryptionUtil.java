package com.bf.dt.util;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtil {
    public static final String key = "LxVYh2dUb4ddvVjlZdV8iQ==";

    //对称加密
    //生成对称加密的秘钥
    public static String createAESKEY(){
        try {
            //创建秘钥生成器对象
            KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
            SecretKey secretKey=keyGenerator.generateKey();
            return Base64Util.base64Enc(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    //加密
    public static String AESEnc(String key,String msg){
        //将字符串的秘钥转换为秘钥对象
        SecretKeySpec keySpec=new SecretKeySpec(Base64Util.base64Dec(key),"AES");
        try {
            //创建加密器
            Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,keySpec);
            return Base64Util.base64Enc(cipher.doFinal(msg.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //解密
    public static String AESDec(String key,String msg){
        //将字符串的秘钥转换为秘钥对象
        SecretKeySpec keySpec=new SecretKeySpec(Base64Util.base64Dec(key),"AES");
        try {
            //创建加密器
            Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,keySpec);
            return new String(cipher.doFinal(Base64Util.base64Dec(msg)),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
