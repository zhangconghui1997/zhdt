package com.bf.dt.dao.onenet;


import com.bf.dt.entity.OneNetDsPO;

public interface OneNetDsMapper {

    OneNetDsPO retrieveInstanceByMA003(String ma003);

}
