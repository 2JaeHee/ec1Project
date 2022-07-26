package com.plateer.ec1.order.vo;

import com.plateer.ec1.common.code.order.OPT0001Enum;
import com.plateer.ec1.common.code.order.OPT0002Enum;
import com.plateer.ec1.common.model.order.OpOrdBase;
import com.plateer.ec1.order.vo.ordersheet.OrdBnfInfoReq;
import com.plateer.ec1.order.vo.ordersheet.OrdDvpAreaInfoReq;
import com.plateer.ec1.order.vo.ordersheet.OrdGoodsInfoReq;
import com.plateer.ec1.order.vo.ordersheet.OrdPayInfoReq;
import com.plateer.ec1.payment.vo.PayApproveReq;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@Getter
public class OrderReq {
    @NotEmpty
    private String orderNo;
    //주문기본
    private OpOrdBase opOrdBase;
    //주문상품
    private List<OrdGoodsInfoReq> ordGoodsInfoReqList;
    //주문혜택정보
    private List<OrdBnfInfoReq> ordBnfInfoReqList;
    //배송지정보
    private List<OrdDvpAreaInfoReq> ordDvpAreaInfoReqList;
    //결제정보
    private OrdPayInfoReq ordPayInfoReq;


    private OPT0002Enum systemType;
    private OPT0001Enum orderType;
//    private PaymentType paymentType;

    private PayApproveReq payInfo;

    public void setBaseInfo() {
        this.orderType = OPT0001Enum.of(this.opOrdBase.getOrdTpCd());
        this.systemType = OPT0002Enum.of(this.opOrdBase.getOrdSysCcd());
    }

    public void setOrdNo(String ordNo) {
        this.orderNo = ordNo;
    }

}
