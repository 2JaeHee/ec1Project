package com.plateer.ec1.order.vo.ordersheet;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrdDvpAreaInfoReq {
    private Integer dvpSeq;
    private String rmtiNm;
    private String rmtiHpNo;
    private String rmtiAddr;
    private String rmtiAddrDtl;
    private List<SumDvpAreaInfoReq> sumDvpAreaInfoReqList;
}
