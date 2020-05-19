package com.bf.dt.service.system.impl;

import com.bf.dt.dao.system.OneNetDrugMapper;
import com.bf.dt.entity.OneNetDrug;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.system.OneNetDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OneNetDrugServiceImpl implements OneNetDrugService {


    @Autowired(required = false)
    OneNetDrugMapper oneNetDrugMapper;

    @Override
    public MsgResult addDrug(OneNetDrug oneNetDrug) {
        try {
            oneNetDrugMapper.addDrug(oneNetDrug);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return MsgResult.success("200",null,"成功");
    }

    @Override
    public MsgResult drugList() {
        try {
            List<OneNetDrug> oneNetDrugs = oneNetDrugMapper.drugList();
            return MsgResult.success("0",oneNetDrugs,"ok");
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error("500","error");
        }

    }
}
