package com.bf.dt.service.onenet;

import com.bf.dt.entity.ArgCameraPO;

import java.util.List;
import java.util.Map;

public interface OneNetCameraService {

    /**
     * 园区ID，网关ID，大棚ID，区域ID，返回采集器列表
     */
    List<ArgCameraPO> retrieveAll(Map<String, Object> map);

    ArgCameraPO retrieveInstanceById(String ma001);


}
