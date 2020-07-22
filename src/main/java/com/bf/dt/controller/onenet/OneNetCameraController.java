package com.bf.dt.controller.onenet;

import com.bf.dt.entity.ArgCameraPO;
import com.bf.dt.service.onenet.OneNetCameraService;
import com.bf.dt.util.OneNetUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("argCamera")
@RestController
public class OneNetCameraController {
    @Autowired
    OneNetCameraService oneNetCameraService;

    @RequestMapping(value = "start")
    public ArgCameraPO start(HttpServletRequest request){
        String id = request.getParameter("id");
        ArgCameraPO argCameraPO = null;
        try {
            argCameraPO = oneNetCameraService.retrieveInstanceById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (argCameraPO != null){
           /* String token = (String)request.getSession().getAttribute("token");*/
            String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODk3ODY4NjMsInB3IjoiMzdkYTFmMjdkMTUxZDJlM2EyMGI3OWZjMDE5MWU4NTMiLCJ0bSI6MTU4OTE4MjA2MywidW4iOiJhZG1pbiJ9.0PeCThQNdSTgPE-5lht9Hw-t8mlpDJzY3OYPf3fn_BM";
            //判断设备是否在线
            String dev = OneNetUtil.req("http://www.bfzhny.com:10000/api/v1/device/info?serial=" + argCameraPO.getMa005() + "&token=" + token);
            org.json.JSONObject devJson = new org.json.JSONObject(dev);
            if ((boolean) devJson.get("Online")){
                String req = OneNetUtil.req("http://www.bfzhny.com:10000/api/v1/stream/start?serial=" + argCameraPO.getMa005() + "&channel=" + argCameraPO.getMa007() + "&code=" + argCameraPO.getMa006() + "&token=" + token);
                if (StringUtils.isBlank(req)){
                    return null;
                }
                org.json.JSONObject jsonObject = new org.json.JSONObject(req);
                argCameraPO.setMa008(String.valueOf(jsonObject.get("RTMP")));
            }else {
                return null;
            }
        }
        return argCameraPO;
    }




    @RequestMapping(value = "control")
    public String control(HttpServletRequest request){
        String direction = request.getParameter("command");//命令
        String id = request.getParameter("liveId");
        String token = (String) request.getSession().getAttribute("token");
        String req = OneNetUtil.req("http://www.bfzhny.com:10000/api/v1/control/ptz?serial=" + id + "&command=" + direction + "&token=" + token);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String stopReq = "\"OK\"";
        if (!direction.equals("stop")){
            stopReq = OneNetUtil.req("http://www.bfzhny.com:10000/api/v1/control/ptz?serial=" + id + "&command=stop&token=" + token);
        }
        if (req.equals("\"OK\"") && stopReq.equals("\"OK\"")){
            return "succ";
        }else {
            return "error";
        }
    }


}
