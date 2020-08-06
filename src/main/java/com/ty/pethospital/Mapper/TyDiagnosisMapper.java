package com.ty.pethospital.Mapper;
import com.ty.pethospital.Entity.TyDiagnosis;

import java.util.List;

public interface TyDiagnosisMapper {
    int deleteByPrimaryKey(String id);

    int insert(TyDiagnosis record);

    int insertSelective(TyDiagnosis record);

    TyDiagnosis selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TyDiagnosis record);

    int updateByPrimaryKey(TyDiagnosis record);

    List<TyDiagnosis> findByAll(TyDiagnosis tyDiagnosis);

    List<TyDiagnosis> findAllByRegisterid(String registerid);
}