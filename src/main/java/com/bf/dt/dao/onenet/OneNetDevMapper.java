package com.bf.dt.dao.onenet;

import com.bf.dt.entity.OneNetDevPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface OneNetDevMapper {
    /**
     * 查询全部设备
     * @param map
     * @return
     * @throws Exception
     */
    List<OneNetDevPo> retrieveAll(Map map) throws Exception;

}
