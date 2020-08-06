package com.ty.pethospital.Service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.pethospital.Entity.TyRegister;
import com.ty.pethospital.Mapper.TyRegisterMapper;
import com.ty.pethospital.Service.TyRegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TyRegisterServiceImpl implements TyRegisterService {

    @Resource
    private TyRegisterMapper tyRegisterMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return tyRegisterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TyRegister record) {
        return tyRegisterMapper.insert(record);
    }

    @Override
    public int insertSelective(TyRegister record) {
        return tyRegisterMapper.insertSelective(record);
    }

    @Override
    public TyRegister selectByPrimaryKey(String id) {
        return tyRegisterMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TyRegister record) {
        return tyRegisterMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TyRegister record) {
        return tyRegisterMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TyRegister> findByAll(TyRegister tyRegister) {
        return tyRegisterMapper.findByAll(tyRegister);
    }

    @Override
    public PageInfo<TyRegister> findByAllwithPage(int page, int pageSize, TyRegister tyRegister) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tyRegisterMapper.findByAll(tyRegister));
    }
}



