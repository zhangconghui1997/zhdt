package com.bf.dt.service.system;

import com.bf.dt.result.MsgResult;

public interface MenuService {

    MsgResult findMenuByUser(String rid);

    MsgResult change(String checkId, String rid);

}