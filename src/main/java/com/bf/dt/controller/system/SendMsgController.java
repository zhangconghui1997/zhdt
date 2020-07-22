package com.bf.dt.controller.system;
import com.bf.dt.util.TxSmsUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg")
public class SendMsgController {

    @RequestMapping("send")
    public void sendMsg(){
        String messge = "";
        String phone = "13383476296";
        TxSmsUtil.sendMsg(null,phone);





    }



}
