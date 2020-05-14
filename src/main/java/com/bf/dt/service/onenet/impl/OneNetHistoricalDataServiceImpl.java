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
    public MsgResult historicalLine(String areaId, String devId) {
        Map<String,String> map = new HashMap<>();
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
                map.put("argAreaId","4e6c76b8f3c34f7a9e26d56a75a5d93d");
                map.put("devId","593519998");
                map.put("starTime",starTime);
                map.put("endTime",endTime);
                List<ArgCurvePO> argCurvePOS = oneNetHistoricalDataMapper.historicalLine(map);
                ArrayList<Map> list = new ArrayList<>();
                Map<String, List<Map<String,String>>> hashMap = new HashMap<>();


                for (ArgCurvePO a: argCurvePOS) {
                    List<Map<String,String>> mapList= new ArrayList<>();
                    Map<String, String> map1 = new HashMap<>();
                    OneNetDsPO oneNetDsPO = oneNetDsMapper.retrieveInstanceByMA003(a.getDSID());
                    String ma004 = oneNetDsPO.getMa004();
                    List<Map<String,String>> maps = hashMap.get(ma004);
                    if (maps!=null){
                        map1.put(a.getVALUETIME(),a.getDSVALUE());
                        maps.add(map1);
                    }else {
                        map1.put(a.getVALUETIME(),a.getDSVALUE());
                        mapList.add(map1);
                        hashMap.put(ma004,mapList);
                        list.add(hashMap);
                    }

                }

                return MsgResult.success("200",list,"查询成功");
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
