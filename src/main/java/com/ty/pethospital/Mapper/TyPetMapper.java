package com.ty.pethospital.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ty.pethospital.Entity.TyPet;

import java.util.List;

public interface TyPetMapper {
    int deleteByPrimaryKey(String id);

    int insert(TyPet record);

    int insertSelective(TyPet record);

    TyPet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TyPet record);

    int updateByPrimaryKey(TyPet record);

    int updateImgpathById(@Param("updatedImgpath")String updatedImgpath,@Param("id")String id);

    List<TyPet> findByAll(TyPet tyPet);
}