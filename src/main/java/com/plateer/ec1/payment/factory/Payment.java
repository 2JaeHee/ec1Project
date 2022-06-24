package com.plateer.ec1.payment.factory;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.ApproveResVO;
import com.plateer.ec1.payment.vo.CancelReq;
import com.plateer.ec1.payment.vo.PayInfo;

public interface Payment {

    ApproveResVO approve(PayInfo payInfo);

    void cancel(CancelReq cancelReq);

    void netCancel(CancelReq cancelReq);

    PaymentType getType();
}
