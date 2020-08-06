package com.ty.pethospital.Service;

import com.github.pagehelper.PageInfo;
import com.ty.pethospital.Entity.TyDiagnosis;

import java.util.List;

public interface TyDiagnosisService{


    int deleteByPrimaryKey(String id);

    int insert(TyDiagnosis record);

    int insertSelective(TyDiagnosis record);

    TyDiagnosis selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TyDiagnosis record);

    int updateByPrimaryKey(TyDiagnosis record);

    PageInfo<TyDiagnosis> findByAllwithPage(int page, int pageSize, TyDiagnosis tyDiagnosis);

    List<TyDiagnosis> findAllByRegisterid(String registerid);

}
