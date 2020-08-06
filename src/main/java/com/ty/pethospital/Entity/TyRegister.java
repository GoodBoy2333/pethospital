package com.ty.pethospital.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TyRegister {
    private String id;

    /**
     * 用户id
     */
    private String userid;

    /**
     * 宠物id
     */
    private String petid;

    /**
     * 情况简介
     */
    private String desc;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date registerTime;

    /**
     * 状态
     */
    private String status;

    private String regstatus;
    private String username;
    private String petname;

    public static TyRegisterBuilder builder() {
        return new TyRegisterBuilder();
    }
}