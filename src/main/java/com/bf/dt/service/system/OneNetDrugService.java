package com.bf.dt.service.system;

import com.bf.dt.entity.OneNetDrug;
import com.bf.dt.result.MsgResult;

public interface OneNetDrugService {

    MsgResult addDrug(OneNetDrug oneNetDrug);
    MsgResult drugList();

}
