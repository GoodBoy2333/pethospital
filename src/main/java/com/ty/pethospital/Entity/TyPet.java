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
public class TyPet {
    private String id;

    /**
    * 宠物名称
    */
    private String name;

    /**
    * 图片路径
    */
    private String imgpath;

    /**
    * 宠物类型
    */
    private String type;

    /**
    * 年龄
    */
    private Integer age;

    /**
    * 介绍
    */
    private String desc;

    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    /**
    * 所属用户id
    */
    private String userid;

    private String gender;
    private String username;
}