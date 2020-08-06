package com.ty.pethospital.Service;

import com.github.pagehelper.PageInfo;
import com.ty.pethospital.Entity.TyPet;

public interface TyPetService {


    int deleteByPrimaryKey(String id);

    int insert(TyPet record);

    int insertSelective(TyPet record);

    TyPet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TyPet record);

    int updateByPrimaryKey(TyPet record);

    PageInfo<TyPet> findByAllwithPage(int page, int pageSize, TyPet tyPet);

    int updateImgpathById(String updatedImgpath, String id);
}
