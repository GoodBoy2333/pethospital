package com.ty.pethospital.Service;

import com.github.pagehelper.PageInfo;
import com.ty.pethospital.Entity.TyUser;

import java.util.List;

public interface TyUserService {


    int deleteByPrimaryKey(String id);

    int insert(TyUser record);

    int insertSelective(TyUser record);

    TyUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TyUser record);

    int updateByPrimaryKey(TyUser record);

    TyUser findOneByUsername(String username);

    int updateByUsername(TyUser updated,String username);

    List<TyUser> findByAll(TyUser tyUser);

    PageInfo<TyUser> findByAllwithPage(int page, int pageSize, TyUser tyUser);

    TyUser findOneUserByparm(String username,String email,String phone);

    int countByUsername(String username);
}
