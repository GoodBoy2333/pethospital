package com.ty.pethospital.Mapper;

import com.ty.pethospital.Entity.TySeq;

public interface TySeqMapper {
    int deleteByPrimaryKey(String name);

    int insert(TySeq record);

    int insertSelective(TySeq record);

    TySeq selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(TySeq record);

    int updateByPrimaryKey(TySeq record);
}