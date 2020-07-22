package com.bf.dt.util;

import com.bf.dt.config.ProjectConfig;
import com.bf.dt.util.TimeUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 *@Author feri
 *@Date Created in 2019/6/14 10:10
 * 基于JWT 进行封装处理
 */
public class JwtUtil {

    /**
     * 基于JWT,生成令牌
     * id 令牌序号
     * content 存储的内容 */
    public static String createJWT(String id,String content){
        //获取指定签名加密算法的枚举对象
        SignatureAlgorithm algorithm=SignatureAlgorithm.HS256;
        JwtBuilder builder=Jwts.builder();
        builder.setId(id);
        builder.setSubject(content); //sub
        builder.setIssuedAt(new Date());//开始时间
        builder.setExpiration(TimeUtil.getMinutes(ProjectConfig.JWTTIMET));//失效时间
        builder.signWith(algorithm,createKey());
        return builder.compact();
    }

    //生成秘钥
    private static SecretKey createKey(){
        byte[] dataKey=ProjectConfig.JWTKEY.getBytes();
        SecretKey key=new SecretKeySpec(dataKey,0,dataKey.length,"AES");
        return key;
    }
    //校验令牌是否合法
    public static boolean checkJWT(String token){
        SecretKey key=createKey();
        try{
            Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    //解析Token
    public static String parseJWT(String token){
        SecretKey key=createKey();
        try{
            Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return claims.getSubject();
        }catch (Exception e){
            return null;
        }
    }
    //更新失效时间
    public static String updateJWT(String token){
        SecretKey key=createKey();
        try{
            SignatureAlgorithm algorithm=SignatureAlgorithm.HS256;
            Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            claims.setIssuedAt(new Date());
            claims.setExpiration(TimeUtil.getMinutes(ProjectConfig.JWTTIMET));
            JwtBuilder builder=Jwts.builder();
            builder.addClaims(claims);
            builder.signWith(algorithm,key);
            return builder.compact();
        }catch (Exception e){
            return null;
        }
    }

    public static void main(String[] args) {
        /*String token=createJWT("1001","{'name':'zhangsan','phone':'18515990152'}");
        System.out.println(token);


        boolean b = checkJWT(token);
        System.out.println(b);*/
        /*

        String token=createJWT("1001","{'name':'zhangsan','phone':'18515990152'}");
        System.out.println(token);
        boolean b = checkJWT(token);
        System.out.println(b);*/
       /* System.out.println("解析："+parseJWT(token));*/
       /* String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDAxIiwic3ViIjoieyduYW1lJzonemhhbmdzYW4nLCdwaG9uZSc6JzE4NTE1OTkwMTUyJ30iLCJpYXQiOjE1OTUzMTA2MDAsImV4cCI6MTU5NTMxMTIwMH0.wU-ArZQFZIV0gkLA0jlc16tfnNORL9OBIPl2vLYqQak";*/
           /*  String token1=updateJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDAxIiwic3ViIjoieyduYW1lJzonemhhbmdzYW4nLCdwaG9uZSc6JzE4NTE1OTkwMTUyJ30iLCJpYXQiOjE1OTUzMTQxMTksImV4cCI6MTU5NTMxNDcxOX0.AbuqP2lTvA7MMmUQjAN2EOVEzp_ZX59bbqemI6WRMas");
            System.out.println("更新之后："+token1);*/



    }
}




