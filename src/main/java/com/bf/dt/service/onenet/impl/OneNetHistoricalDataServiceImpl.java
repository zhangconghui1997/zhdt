package com.bf.dt.service.onenet.impl;

import com.bf.dt.dao.onenet.OneNetDsMapper;
import com.bf.dt.dao.onenet.OneNetHistoricalDataMapper;
import com.bf.dt.entity.ArgCurvePO;
import com.bf.dt.entity.OneNetDsPO;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.onenet.OneNetHistoricalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OneNetHistoricalDataServiceImpl implements OneNetHistoricalDataService {
    @Autowired
    OneNetHistoricalDataMapper oneNetHistoricalDataMapper;
    @Autowired
    OneNetDsMapper oneNetDsMapper;
    @Override
    public MsgResult historicalLine(String areaId, String devId,String dsId) {
        Map<String,String> map = new HashMap<>();
        areaId = "00";
        devId = "33";
        if (areaId == null || devId==null){
            return MsgResult.error("500","请选择设备");
        }else {
            try {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//栅格化日期
                Calendar calendar = Calendar.getInstance();
                String endTime = sdf.format(date);
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                String starTime = sdf.format(calendar.getTime());
                map.put("argYuanQuId","83cea031843e4635b5e07fbea6dfa2e6");
                map.put("devId","592021667");
//                map.put("starTime",starTime);
//                map.put("endTime",endTime);
                map.put(("dsId"),"soilhumi2");
                List<ArgCurvePO> argCurvePOS = oneNetHistoricalDataMapper.historicalLine(map);


                return MsgResult.success("200",argCurvePOS,"查询成功");
            } catch (Exception e) {
                e.printStackTrace();
                return MsgResult.error("500","查询失败");
            }
        }

    }

    @Override
    public MsgResult findAll() {
        return null;
    }
}
