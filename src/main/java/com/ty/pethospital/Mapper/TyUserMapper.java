package com.ty.pethospital.Mapper;

import com.ty.pethospital.Entity.TyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TyUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(TyUser record);

    int insertSelective(TyUser record);

    TyUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TyUser record);

    int updateByPrimaryKey(TyUser record);

    TyUser findOneByUsername(@Param("username") String username);

    int updateByUsername(@Param("updated") TyUser updated, @Param("username") String username);

    List<TyUser> findByAll(TyUser tyUser);

    TyUser findOneUserByparm(@Param("username") String username, @Param("email") String email, @Param("phone") String phone);

    int countByUsername(@Param("username") String username);
}