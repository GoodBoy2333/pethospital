package com.ty.pethospital.Service;

import com.github.pagehelper.PageInfo;
import com.ty.pethospital.Entity.TyRegister;

import java.util.List;

public interface TyRegisterService {


    int deleteByPrimaryKey(String id);

    int insert(TyRegister record);

    int insertSelective(TyRegister record);

    TyRegister selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TyRegister record);

    int updateByPrimaryKey(TyRegister record);

    List<TyRegister> findByAll(TyRegister tyRegister);

    PageInfo<TyRegister> findByAllwithPage(int page, int pageSize, TyRegister tyRegister);

}



