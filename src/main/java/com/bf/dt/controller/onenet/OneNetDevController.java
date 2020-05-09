package com.bf.dt.controller.onenet;

import com.bf.dt.entity.OneNetDevPo;
import com.bf.dt.service.onenet.OneNetDevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("oneNetDev")
@RestController
public class OneNetDevController {

    @Autowired
    OneNetDevService oneNetDevService;

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




}
