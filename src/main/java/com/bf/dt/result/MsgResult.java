package com.bf.dt.result;

public class MsgResult {
    String code;
    Object data;
    String message;
    int count;

    public MsgResult() {
    }

    public MsgResult(String code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public void setData(Object data) {
        this.data = data;
    }

    public static MsgResult error(String code,String message){
        MsgResult msgResult = new MsgResult();
        msgResult.setCode(code);
        msgResult.setMessage(message);
        msgResult.setData(null);
        return msgResult;
    }

    public static MsgResult success(String code,Object data,String message){
        MsgResult msgResult = new MsgResult();
        msgResult.setCode(code);
        msgResult.setMessage(message);
        msgResult.setData(data);
        return msgResult;
    }

    public static MsgResult page(String code,Object data,String message,int count){
        MsgResult msgResult = new MsgResult();
        msgResult.setCode(code);
        msgResult.setMessage(message);
        msgResult.setData(data);
        msgResult.setCount(count);
        return msgResult;
    }


}
