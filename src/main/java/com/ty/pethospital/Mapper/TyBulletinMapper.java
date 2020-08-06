package com.ty.pethospital.Mapper;
import com.ty.pethospital.Entity.TyBulletin;

import java.util.List;

public interface TyBulletinMapper {
    int deleteByPrimaryKey(String id);

    int insert(TyBulletin record);

    int insertSelective(TyBulletin record);

    TyBulletin selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TyBulletin record);

    int updateByPrimaryKey(TyBulletin record);

    List<TyBulletin> findByAll(TyBulletin tyBulletin);

}