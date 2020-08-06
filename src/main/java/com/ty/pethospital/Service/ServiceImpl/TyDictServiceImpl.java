package com.ty.pethospital.Service.ServiceImpl;

import com.ty.pethospital.Entity.TyDict;
import com.ty.pethospital.Mapper.TyDictMapper;
import com.ty.pethospital.Service.TyDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TyDictServiceImpl implements TyDictService{

    @Resource
    private TyDictMapper tyDictMapper;

    @Override
    public int deleteByPrimaryKey(String dictKey) {
        return tyDictMapper.deleteByPrimaryKey(dictKey);
    }

    @Override
    public int insert(TyDict record) {
        return tyDictMapper.insert(record);
    }

    @Override
    public int insertSelective(TyDict record) {
        return tyDictMapper.insertSelective(record);
    }

    @Override
    public TyDict selectByPrimaryKey(String dictKey) {
        return tyDictMapper.selectByPrimaryKey(dictKey);
    }

    @Override
    public int updateByPrimaryKeySelective(TyDict record) {
        return tyDictMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TyDict record) {
        return tyDictMapper.updateByPrimaryKey(record);
    }

    public List<TyDict> findByAll(TyDict tyDict) {
        return tyDictMapper.findByAll(tyDict);
    }
}
