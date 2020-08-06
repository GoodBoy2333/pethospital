package com.ty.pethospital.Mapper;
import com.ty.pethospital.Entity.TyWatch;

import java.util.List;

public interface TyWatchMapper {
    int deleteByPrimaryKey(String id);

    int insert(TyWatch record);

    int insertSelective(TyWatch record);

    TyWatch selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TyWatch record);

    int updateByPrimaryKey(TyWatch record);

    List<TyWatch> findByAll(TyWatch tyWatch);
}