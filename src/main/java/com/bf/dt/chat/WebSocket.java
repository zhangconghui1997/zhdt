package com.bf.dt.chat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
@ServerEndpoint(value = "/websocket/{username}")
@Component
public class WebSocket {

    /**
     * 在线人数
     */
    public static int onlineNumber = 0;

    //用来存放每个客户端对应的MyWebSocket对象。
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //用户名称
    private String username;




    /**
     *      * 连接建立成功调用的方法
     *     
     */
    @OnOpen
    public void onOpen(@PathParam("username")String username, Session session) {
        System.out.println("username"+username);
        if (username!= null && username!= ""){
            System.out.println("现在来连接的客户id："+session.getId()+"用户名："+ username);
            this.username = username;
            this.session = session;
            System.out.println("有新连接加入！ 当前在线人数" + onlineNumber);
        }





        //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息



        try {


            //通知所有用户，xxx上线了
            Map<String,Object> map1 = new HashMap();
            map1.put("messageType",1);
            map1.put("username", this.username);
            sendMessageAll(JSON.toJSONString(map1), this.username);




            //把自己的信息加入到map当中去
            clients.put(this.username, this);
            //给自己发一条消息：告诉自己现在都有谁在线
            Map<String,Object> map2 = new HashMap();
            map2.put("messageType",3);
            //移除掉自己
            Set<String> set = clients.keySet();
            map2.put("onlineUsers",set);
            sendMessageTo(JSON.toJSONString(map2), this.username);


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(this.username +"上线的时候通知所有人发生了错误");
        }

    }


    /**
     *      * 连接关闭调用的方法
     *     
     */
    @OnClose
    public void onClose() {
        onlineNumber--;
        clients.remove(username);//从set中删除

        try {
            Map<String,Object> map1 = new HashMap();
            map1.put("messageType",2);
            map1.put("onlineUsers",clients.keySet());
            map1.put("username",username);
            sendMessageAll(JSON.toJSONString(map1),username);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(username+"下线的时候通知所有人发生了错误");
        }


        System.out.println("有连接关闭！ 当前在线人数" + onlineNumber);
    }


    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */

    @OnMessage
    public void onMessage(String message, Session session) {


        try {
            System.out.println("来自客户端消息：" + message+"客户端的id是："+session.getId());
            JSONObject jsonObject = JSON.parseObject(message);
            String textMessage = jsonObject.getString("message");
            String fromusername = jsonObject.getString("username");
            String tousername = jsonObject.getString("to");
            Map<String,Object> map1 = new HashMap();
            map1.put("messageType",4);
            map1.put("textMessage",textMessage);
            map1.put("fromusername",fromusername);

            if(tousername.equals("All")){
                map1.put("tousername","所有人");
                sendMessageAll(JSON.toJSONString(map1),fromusername);
            }
            else{
                map1.put("tousername",tousername);
                sendMessageTo(JSON.toJSONString(map1),tousername);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(username+"发送消息失败");
        }


    }


    /**
     * 发生错误时调用
     */

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    /**
     * 群发自定义消息
     */


    public void sendMessageAll(String message,String FromUserName) {
        for (WebSocket item : clients.values()) {
            //同步异步说明参考：http://blog.csdn.net/who_is_xiaoming/article/details/5328769
            //this.session.getBasicRemote().sendText(message);
            item.session.getAsyncRemote().sendText(message);//异步发送消息.
        }
    }






    public void sendMessageTo(String message, String ToUserName) throws IOException {
        for (WebSocket item : clients.values()) {
            if (item.username.equals(ToUserName) ) {
                item.session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }


}
