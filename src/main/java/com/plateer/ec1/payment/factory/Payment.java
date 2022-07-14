package com.plateer.ec1.payment.factory;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.ApproveResVO;
import com.plateer.ec1.payment.vo.CancelReq;
import com.plateer.ec1.payment.vo.PayApproveReq;
import com.plateer.ec1.payment.vo.PayCompleteReq;

public interface Payment {

    ApproveResVO approvePay(PayApproveReq payInfo);

    void completePay(PayCompleteReq payInfo);

    void cancelPay(CancelReq cancelReq);

    void netCancel(CancelReq cancelReq);

    PaymentType getType();
}
