package com.ty.pethospital.Mapper;
import com.ty.pethospital.Entity.TyMessage;

import java.util.List;

public interface TyMessageMapper {
    int deleteByPrimaryKey(String id);

    int insert(TyMessage record);

    int insertSelective(TyMessage record);

    TyMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TyMessage record);

    int updateByPrimaryKey(TyMessage record);

    List<TyMessage> findByAll(TyMessage tyMessage);


}