package com.ty.pethospital.Service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.pethospital.Entity.TyMessage;
import com.ty.pethospital.Mapper.TyMessageMapper;
import com.ty.pethospital.Service.TyMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TyMessageServiceImpl implements TyMessageService {

    @Resource
    private TyMessageMapper tyMessageMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return tyMessageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TyMessage record) {
        return tyMessageMapper.insert(record);
    }

    @Override
    public int insertSelective(TyMessage record) {
        return tyMessageMapper.insertSelective(record);
    }

    @Override
    public TyMessage selectByPrimaryKey(String id) {
        return tyMessageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TyMessage record) {
        return tyMessageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TyMessage record) {
        return tyMessageMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TyMessage> findByAllwithPage(int page, int pageSize, TyMessage tyMessage) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tyMessageMapper.findByAll(tyMessage));
    }
}


