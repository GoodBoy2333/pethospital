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
public class TyWatch {
    private String id;

    /**
     * 状态
     */
    private String status;

    /**
     * 所属用户id
     */
    private String userid;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    /**
     * 宠物id
     */
    private String petid;

    private String userName;
    private String petName;
    private String wachstatus;

    public static TyWatchBuilder builder() {
        return new TyWatchBuilder();
    }
}