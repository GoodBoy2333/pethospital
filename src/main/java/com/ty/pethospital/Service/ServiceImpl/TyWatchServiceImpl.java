package com.ty.pethospital.Service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.pethospital.Entity.TyWatch;
import com.ty.pethospital.Mapper.TyWatchMapper;
import com.ty.pethospital.Service.TyWatchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TyWatchServiceImpl implements TyWatchService {

    @Resource
    private TyWatchMapper tyWatchMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return tyWatchMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TyWatch record) {
        return tyWatchMapper.insert(record);
    }

    @Override
    public int insertSelective(TyWatch record) {
        return tyWatchMapper.insertSelective(record);
    }

    @Override
    public TyWatch selectByPrimaryKey(String id) {
        return tyWatchMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TyWatch record) {
        return tyWatchMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TyWatch record) {
        return tyWatchMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TyWatch> findByAllwithPage(int page, int pageSize, TyWatch tyWatch) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tyWatchMapper.findByAll(tyWatch));
    }
}

