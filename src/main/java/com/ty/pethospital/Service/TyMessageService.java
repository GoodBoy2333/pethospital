package com.ty.pethospital.Service;

import com.github.pagehelper.PageInfo;
import com.ty.pethospital.Entity.TyMessage;

public interface TyMessageService {


    int deleteByPrimaryKey(String id);

    int insert(TyMessage record);

    int insertSelective(TyMessage record);

    TyMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TyMessage record);

    int updateByPrimaryKey(TyMessage record);

    PageInfo<TyMessage> findByAllwithPage(int page, int pageSize, TyMessage tyMessage);

}


