package com.ty.pethospital.Service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.pethospital.Entity.TyPet;
import com.ty.pethospital.Mapper.TyPetMapper;
import com.ty.pethospital.Service.TyPetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TyPetServiceImpl implements TyPetService {

    @Resource
    private TyPetMapper tyPetMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return tyPetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TyPet record) {
        return tyPetMapper.insert(record);
    }

    @Override
    public int insertSelective(TyPet record) {
        return tyPetMapper.insertSelective(record);
    }

    @Override
    public TyPet selectByPrimaryKey(String id) {
        return tyPetMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TyPet record) {
        return tyPetMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TyPet record) {
        return tyPetMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TyPet> findByAllwithPage(int page, int pageSize, TyPet tyPet) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tyPetMapper.findByAll(tyPet));
    }

    @Override
    public int updateImgpathById(String updatedImgpath, String id) {
        return tyPetMapper.updateImgpathById(updatedImgpath, id);
    }
}
