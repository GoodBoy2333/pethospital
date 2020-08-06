package com.ty.pethospital.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TyDict {
    /**
    * 键
    */
    private String dictKey;

    /**
    * 值
    */
    private String dictValue;

    /**
    * 类型
    */
    private String dictType;
}