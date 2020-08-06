package com.ty.pethospital.Service;

import com.github.pagehelper.PageInfo;
import com.ty.pethospital.Entity.TyWatch;

public interface TyWatchService {


    int deleteByPrimaryKey(String id);

    int insert(TyWatch record);

    int insertSelective(TyWatch record);

    TyWatch selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TyWatch record);

    int updateByPrimaryKey(TyWatch record);

    PageInfo<TyWatch> findByAllwithPage(int page, int pageSize, TyWatch tyWatch);

}

