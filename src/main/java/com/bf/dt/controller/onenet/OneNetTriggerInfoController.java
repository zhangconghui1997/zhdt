package com.bf.dt.controller.onenet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("oneNetTriggerInfo")
@RestController
public class OneNetTriggerInfoController {


    @Value(value = "${local.down}")
    private String localDown;

    @RequestMapping("getDown")
    public String getDown(){
        return localDown;
    }


}
