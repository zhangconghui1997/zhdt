package com.bf.dt.controller;

import com.bf.dt.result.MsgResult;
import com.bf.dt.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuContorller {

    @Autowired(required = false)
    MenuService menuService;


    @RequestMapping("findMenuByRole")
    public MsgResult findMenuByRole(String rid){
        MsgResult msgResult = menuService.findMenuByUser(rid);

        return msgResult;
    }


    @RequestMapping("change")
    public MsgResult findMenuByRole(String checkId,String rid){
        System.out.println(checkId);
        System.out.println(rid);

        return null;
    }



}
