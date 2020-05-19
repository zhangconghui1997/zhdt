package com.bf.dt.dao.system;

import com.bf.dt.entity.OneNetDrug;

import java.util.List;

public interface OneNetDrugMapper {
    void addDrug(OneNetDrug oneNetDrug);
    List<OneNetDrug>  drugList();
}
