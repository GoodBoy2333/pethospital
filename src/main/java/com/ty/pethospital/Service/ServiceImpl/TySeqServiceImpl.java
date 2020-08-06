package com.ty.pethospital.Service.ServiceImpl;

import com.ty.pethospital.Entity.TySeq;
import com.ty.pethospital.Mapper.TySeqMapper;
import com.ty.pethospital.Service.TySeqService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TySeqServiceImpl implements TySeqService {

    private static ReentrantLock lock = new ReentrantLock();
    @Resource
    private TySeqMapper tySeqMapper;

    @Override
    public int deleteByPrimaryKey(String name) {
        return tySeqMapper.deleteByPrimaryKey(name);
    }

    @Override
    public int insert(TySeq record) {
        return tySeqMapper.insert(record);
    }

    @Override
    public int insertSelective(TySeq record) {
        return tySeqMapper.insertSelective(record);
    }

    @Override
    public TySeq selectByPrimaryKey(String name) {
        return tySeqMapper.selectByPrimaryKey(name);
    }

    @Override
    public int updateByPrimaryKeySelective(TySeq record) {
        return tySeqMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TySeq record) {
        return tySeqMapper.updateByPrimaryKey(record);
    }

    @Override
    public int getNextId(String seqName) {
        try {
            lock.lock();
            TySeq tySeq = tySeqMapper.selectByPrimaryKey(seqName);
            if (tySeq == null) {
                // 插入新纪录
                TySeq seq = TySeq.builder().name(seqName).value(1).createtime(new Date()).updatetime(new Date()).build();
                if (tySeqMapper.insert(seq) > 0) {
                    return 1;
                }
            } else {
                LocalDate now = LocalDate.now();
                LocalDate updateTime = date2LocalDate(tySeq.getUpdatetime());
                if (now.isAfter(updateTime)) {
                    // 日期过期，重置数据
                    tySeq.setUpdatetime(new Date());
                    tySeq.setValue(1);
                    if (tySeqMapper.updateByPrimaryKey(tySeq) > 0) {
                        return 1;
                    }
                } else if (now.isEqual(updateTime)) {
                    // 日期相等
                    int value = tySeq.getValue();
                    tySeq.setValue(value + 1);
                    if (tySeqMapper.updateByPrimaryKey(tySeq) > 0) {
                        return value + 1;
                    }
                }
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String getNextIdStr(String seqName) {
        int nextId = this.getNextId(seqName);
        if (nextId > 0) {
            String result = new String();
            int year = LocalDate.now().getYear();
            int monthValue = LocalDate.now().getMonthValue();
            int dayOfMonth = LocalDate.now().getDayOfMonth();
            if (monthValue > 10) {
                result = year + "" + monthValue;
            } else {
                result = year + "0" + monthValue;
            }
            if (dayOfMonth > 10) {
                result = result + "" + dayOfMonth;
            } else {
                result = result + "0" + dayOfMonth;
            }
            return result + "-" + nextId;
        } else {
            throw new RuntimeException("获取id失败");
        }
    }

    public static LocalDate date2LocalDate(Date date) {
        if (null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}


