package com.bf.dt.service.onenet;

import com.bf.dt.entity.C001PO;
import com.bf.dt.entity.DtSitePO;

import java.util.List;

public interface OneNetAreaService {

    C001PO retrieveC001ByMA001(String var1);

    List<DtSitePO> retrieveByAuth(String userId);


    //通过园区ID，获取大田站点(web端)
    List<DtSitePO> retrieveByYuanQuId(String ma002) throws Exception;
}
