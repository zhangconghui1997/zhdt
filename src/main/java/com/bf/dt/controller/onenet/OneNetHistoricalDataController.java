package com.bf.dt.controller.onenet;

import com.bf.dt.entity.OneNetDevPo;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.onenet.OneNetHistoricalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("oneNetHistoricalData")
@RestController
public class OneNetHistoricalDataController {
    @Autowired
    OneNetHistoricalDataService oneNetHistoricalDataService;

    @RequestMapping("historicalLine")
    public MsgResult historicalLine(String areaId,String devId,String dsId){
        MsgResult msgResult = oneNetHistoricalDataService.historicalLine(areaId, devId,dsId);

        return msgResult;

    }

}
