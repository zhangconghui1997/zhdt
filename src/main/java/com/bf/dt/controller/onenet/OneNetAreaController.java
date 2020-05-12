package com.bf.dt.controller.onenet;

import com.bf.dt.entity.C001PO;
import com.bf.dt.entity.DtSitePO;
import com.bf.dt.service.onenet.OneNetAreaService;
import com.bf.dt.util.OneNetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("dtSite")
@RestController
public class OneNetAreaController {
    @Autowired(required = false)
    OneNetAreaService oneNetAreaService;


    /**
     * 获取园区坐标信息
     * @return
     */

    @RequestMapping("getYuanQu")
    public C001PO getYuanquDetailsById(){
        String yuanQuId = "2a42ace6df21493ca9d01aefbd8719bb";//园区ID
        try {
            return oneNetAreaService.retrieveC001ByMA001(yuanQuId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 获取园区下用户所管理的场地信息
     * @return
     */
    @RequestMapping("getMapPoint")
    public List getMapPoint(){

        List<DtSitePO> dtSitePOS = new ArrayList<>();
        String userId = "60c2ffe976d1444daf0398e88bfe7902";
        String orgId = "2a42ace6df21493ca9d01aefbd8719bb";
        dtSitePOS = OneNetUtil.getSiteListByAuth(oneNetAreaService,userId,orgId);
        return dtSitePOS;
    }









}
