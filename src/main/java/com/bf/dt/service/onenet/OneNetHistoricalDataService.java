package com.bf.dt.service.onenet;

import com.bf.dt.entity.C001PO;
import com.bf.dt.entity.DtSitePO;
import com.bf.dt.result.MsgResult;

import java.util.List;

public interface OneNetHistoricalDataService {

    MsgResult historicalLine(String areaId,String devId);
    MsgResult findAll();
}
