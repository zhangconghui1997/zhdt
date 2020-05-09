package com.bf.dt.service.onenet.impl;

import com.bf.dt.dao.onenet.OneNetDevMapper;
import com.bf.dt.entity.OneNetDevPo;
import com.bf.dt.service.onenet.OneNetDevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class OneNetDevServiceImpl implements OneNetDevService {
    @Autowired(required = false)
    private OneNetDevMapper oneNetDevMapper;

    @Override
    @Transactional
    public List<OneNetDevPo> retrieveAll(Map map) throws Exception {
        return oneNetDevMapper.retrieveAll(map);
    }



}
