package com.bf.dt.controller.system;

import com.bf.dt.result.MsgResult;
import com.bf.dt.service.system.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menu")
public class MenuContorller {

    @Autowired(required = false)
    MenuService menuService;


    /**
     * 查询角色对应的所有菜单
     * @param rid
     * @return
     */
    @RequestMapping("findMenuByRole")
    public MsgResult findMenuByRole(String rid){
        MsgResult msgResult = menuService.findMenuByUser(rid);

        return msgResult;
    }


    /**
     * 修改角色对应的权限
     * @param checkId
     * @param rid
     * @return
     */
    @RequestMapping("change")
    public MsgResult change(String checkId,String rid){
        MsgResult msgResult = menuService.change(checkId, rid);
        System.out.println(checkId);
        System.out.println(rid);

        return msgResult;
    }



}
