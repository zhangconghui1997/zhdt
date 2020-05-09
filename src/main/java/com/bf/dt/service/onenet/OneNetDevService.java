package com.bf.dt.service.onenet;

import com.bf.dt.entity.OneNetDevPo;

import java.util.List;
import java.util.Map;

public interface OneNetDevService{


    /**
     * 查询全部设备
     * @param map
     * @return
     * @throws Exception
     */
    List<OneNetDevPo> retrieveAll(Map map) throws Exception;


}

