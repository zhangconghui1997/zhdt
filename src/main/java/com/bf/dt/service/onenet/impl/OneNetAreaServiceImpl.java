package com.bf.dt.service.onenet.impl;

import com.bf.dt.dao.onenet.OneNetAreaMapper;
import com.bf.dt.entity.C001PO;
import com.bf.dt.entity.DtSitePO;
import com.bf.dt.service.onenet.OneNetAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OneNetAreaServiceImpl implements OneNetAreaService {
    @Autowired(required = false)
    OneNetAreaMapper oneNetAreaMapper;

    @Override
    public C001PO retrieveC001ByMA001(String var1) {
        return oneNetAreaMapper.retrieveC001ByMA001(var1);
    }

    @Override
    public List<DtSitePO> retrieveByAuth(String userId) {
        return oneNetAreaMapper.retrieveByAuth(userId);
    }

    @Override
    public List<DtSitePO> retrieveByYuanQuId(String ma002) throws Exception {
        return oneNetAreaMapper.retrieveByYuanQuId(ma002);
    }
}
