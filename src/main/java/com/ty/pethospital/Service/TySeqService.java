package com.ty.pethospital.Service;

import com.ty.pethospital.Entity.TySeq;

public interface TySeqService {


    int deleteByPrimaryKey(String name);

    int insert(TySeq record);

    int insertSelective(TySeq record);

    TySeq selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(TySeq record);

    int updateByPrimaryKey(TySeq record);

    int getNextId(String seqName);

    String getNextIdStr(String seqName);
}


