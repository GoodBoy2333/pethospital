package com.ty.pethospital.Service;

import com.ty.pethospital.Entity.TyDict;

import java.util.List;

public interface TyDictService{


    int deleteByPrimaryKey(String dictKey);

    int insert(TyDict record);

    int insertSelective(TyDict record);

    TyDict selectByPrimaryKey(String dictKey);

    int updateByPrimaryKeySelective(TyDict record);

    int updateByPrimaryKey(TyDict record);

    List<TyDict> findByAll(TyDict tyDict);

}
