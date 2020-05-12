package com.bf.dt.service.onenet.impl;

import com.bf.dt.dao.onenet.OneNetCameraMapper;
import com.bf.dt.entity.ArgCameraPO;
import com.bf.dt.service.onenet.OneNetCameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class OneNetCameraServiceImpl implements OneNetCameraService {

    @Autowired
    OneNetCameraMapper oneNetCameraMapper;


    @Override
    public List<ArgCameraPO> retrieveAll(Map<String, Object> map) {
        return oneNetCameraMapper.retrieveAll(map);
    }

    @Override
    public ArgCameraPO retrieveInstanceById(String ma001) {
        return oneNetCameraMapper.retrieveInstanceById(ma001);
    }
}
