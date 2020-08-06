package com.ty.pethospital.Service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.pethospital.Entity.TyBulletin;
import com.ty.pethospital.Mapper.TyBulletinMapper;
import com.ty.pethospital.Service.TyBulletinService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TyBulletinServiceImpl implements TyBulletinService{

    @Resource
    private TyBulletinMapper tyBulletinMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return tyBulletinMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TyBulletin record) {
        return tyBulletinMapper.insert(record);
    }

    @Override
    public int insertSelective(TyBulletin record) {
        return tyBulletinMapper.insertSelective(record);
    }

    @Override
    public TyBulletin selectByPrimaryKey(String id) {
        return tyBulletinMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TyBulletin record) {
        return tyBulletinMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TyBulletin record) {
        return tyBulletinMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TyBulletin> findByAll(TyBulletin tyBulletin) {
        return tyBulletinMapper.findByAll(tyBulletin);
    }

    @Override
    public PageInfo<TyBulletin> findByAllwithPage(int page, int pageSize, TyBulletin tyBulletin) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tyBulletinMapper.findByAll(tyBulletin));
    }
}
