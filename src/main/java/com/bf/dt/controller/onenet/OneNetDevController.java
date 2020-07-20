package com.bf.dt.controller.onenet;

import com.bf.dt.entity.ArgCameraPO;
import com.bf.dt.entity.DtOneNetDevPo;
import com.bf.dt.entity.DtSitePO;
import com.bf.dt.entity.OneNetDevPo;
import com.bf.dt.service.onenet.OneNetAreaService;
import com.bf.dt.service.onenet.OneNetCameraService;
import com.bf.dt.service.onenet.OneNetDevService;
import com.bf.dt.util.OneNetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("dtOneNetDev")
@RestController
public class OneNetDevController {

    @Autowired
    OneNetDevService oneNetDevService;

    @Autowired(required = false)
    OneNetAreaService oneNetAreaService;


    @Autowired(required = false)
    OneNetCameraService oneNetCameraService;

    @RequestMapping("findAll")
    public List<OneNetDevPo> findAll(){
        try {
            List<OneNetDevPo> oneNetDevPos = oneNetDevService.retrieveAll(null);
            System.out.println(oneNetDevPos.get(0));
            return oneNetDevPos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }







    /**
     * 获取区域及设备坐标信息（首页地图使用）
     * @return java.util.Map - 园区下区域，OneNet设备，摄像头集合
     */
    @RequestMapping(value = "getMapPoint")
    public Map getDevPoingt(){

        List<DtSitePO> dtSitePOS = new ArrayList<>();
        String userId = "b68813ba889349f899af0a50f1394e74";
        String orgId = "83cea031843e4635b5e07fbea6dfa2e6";


        dtSitePOS = OneNetUtil.getSiteListByAuth(oneNetAreaService,userId,orgId);

        List<DtOneNetDevPo> dtOneNetDevPos = new ArrayList<>();
        List<ArgCameraPO> argCameraPOS = new ArrayList<>();
        Map map = new HashMap();
        Map<String, Object> parMap = new HashMap<>();
        try {
            for (DtSitePO dtSitePO : dtSitePOS) {
                parMap.put("argAreaId",dtSitePO.getMa001());
                List camPOS = oneNetCameraService.retrieveAll(parMap);
                parMap.put("siteId",dtSitePO.getMa001());
                parMap.put("fireState","1");
                List devPos = oneNetDevService.retrieveAll(parMap);
                argCameraPOS.addAll(camPOS);
                dtOneNetDevPos.addAll(devPos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("site",dtSitePOS);
        map.put("dev",dtOneNetDevPos);
        map.put("camera",argCameraPOS);
        return map;
    }







}
