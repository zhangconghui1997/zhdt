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
      }

}
