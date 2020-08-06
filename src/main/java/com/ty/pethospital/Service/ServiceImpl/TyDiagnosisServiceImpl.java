package com.ty.pethospital.Service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.pethospital.Entity.TyDiagnosis;
import com.ty.pethospital.Mapper.TyDiagnosisMapper;
import com.ty.pethospital.Service.TyDiagnosisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TyDiagnosisServiceImpl implements TyDiagnosisService {

    @Resource
    private TyDiagnosisMapper tyDiagnosisMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return tyDiagnosisMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TyDiagnosis record) {
        return tyDiagnosisMapper.insert(record);
    }

    @Override
    public int insertSelective(TyDiagnosis record) {
        return tyDiagnosisMapper.insertSelective(record);
    }

    @Override
    public TyDiagnosis selectByPrimaryKey(String id) {
        return tyDiagnosisMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TyDiagnosis record) {
        return tyDiagnosisMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TyDiagnosis record) {
        return tyDiagnosisMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TyDiagnosis> findByAllwithPage(int page, int pageSize, TyDiagnosis tyDiagnosis) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tyDiagnosisMapper.findByAll(tyDiagnosis));
    }

    @Override
    public List<TyDiagnosis> findAllByRegisterid(String registerid) {
        return tyDiagnosisMapper.findAllByRegisterid(registerid);
    }
}
