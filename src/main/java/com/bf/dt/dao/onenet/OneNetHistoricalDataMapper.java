package com.bf.dt.dao.onenet;

import com.bf.dt.entity.ArgCurvePO;
import com.bf.dt.result.MsgResult;

import java.util.List;
import java.util.Map;

public interface OneNetHistoricalDataMapper {
    List<ArgCurvePO> historicalLine(Map<String,String> map);
    List<ArgCurvePO> findAll();
}
