package com.bf.dt.dao.onenet;

import com.bf.dt.entity.ArgCameraPO;

import java.util.List;
import java.util.Map;

public interface OneNetCameraMapper {

    List<ArgCameraPO> retrieveAll(Map<String, Object> map);
    ArgCameraPO retrieveInstanceById(String ma001);
}
