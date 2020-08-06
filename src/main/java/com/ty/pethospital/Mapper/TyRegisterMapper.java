package com.ty.pethospital.Mapper;

import com.ty.pethospital.Entity.TyRegister;import java.util.List;

public interface TyRegisterMapper {
    int deleteByPrimaryKey(String id);

    int insert(TyRegister record);

    int insertSelective(TyRegister record);

    TyRegister selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TyRegister record);

    int updateByPrimaryKey(TyRegister record);

    List<TyRegister> findByAll(TyRegister tyRegister);
}