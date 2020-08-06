package com.ty.pethospital.Service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.pethospital.Entity.TyUser;
import com.ty.pethospital.Mapper.TyUserMapper;
import com.ty.pethospital.Service.TyUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TyUserServiceImpl implements TyUserService {

    @Resource
    private TyUserMapper tyUserMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return tyUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TyUser record) {
        return tyUserMapper.insert(record);
    }

    @Override
    public int insertSelective(TyUser record) {
        return tyUserMapper.insertSelective(record);
    }

    @Override
    public TyUser selectByPrimaryKey(String id) {
        return tyUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TyUser record) {
        return tyUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TyUser record) {
        return tyUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public TyUser findOneByUsername(String username) {
        return tyUserMapper.findOneByUsername(username);
    }

    @Override
    public int updateByUsername(TyUser updated, String username) {
        return tyUserMapper.updateByUsername(updated, username);
    }

    @Override
    public List<TyUser> findByAll(TyUser tyUser) {
        return tyUserMapper.findByAll(tyUser);
    }

    @Override
    public PageInfo<TyUser> findByAllwithPage(int page, int pageSize, TyUser tyUser) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tyUserMapper.findByAll(tyUser));
    }

    @Override
    public TyUser findOneUserByparm(String username, String email, String phone) {
        return tyUserMapper.findOneUserByparm(username, email, phone);
    }

    @Override
    public int countByUsername(String username) {
        return tyUserMapper.countByUsername(username);
    }
}
