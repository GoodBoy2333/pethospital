package com.ty.pethospital.Entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TySeq {
    private String name;

    private Integer value;

    private Date updatetime;

    private Date createtime;

    public static TySeqBuilder builder() {
        return new TySeqBuilder();
    }
}