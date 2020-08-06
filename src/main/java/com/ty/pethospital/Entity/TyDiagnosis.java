package com.ty.pethospital.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TyDiagnosis {
    private String id;

    /**
    * 用户id（保留字段）
    */
    private String userid;

    /**
    * 挂号id
    */
    private String registerid;

    /**
    * 金额
    */
    private BigDecimal money;

    /**
    * 情况诊断
    */
    private String desc;

    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    /**
    * 状态
    */
    private String status;


    /**
     * 用户名
     */
    private String userName;
    /**
     * 挂号状态中文
     */
    private String diastatus;
    /**
     * 挂号简介
     */
    private String regdesc;
    /**
     * 挂号用户id
     */
    private String reguserid;
    /**
     * 宠物名称
     */
    private String petname;
}