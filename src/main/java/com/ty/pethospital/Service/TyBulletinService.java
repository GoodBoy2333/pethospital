package com.ty.pethospital.Service;

import com.github.pagehelper.PageInfo;
import com.ty.pethospital.Entity.TyBulletin;

import java.util.List;

public interface TyBulletinService{


    int deleteByPrimaryKey(String id);

    int insert(TyBulletin record);

    int insertSelective(TyBulletin record);

    TyBulletin selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TyBulletin record);

    int updateByPrimaryKey(TyBulletin record);

    List<TyBulletin> findByAll(TyBulletin tyBulletin);

    PageInfo<TyBulletin> findByAllwithPage(int page, int pageSize, TyBulletin tyBulletin);

}
