package com.plateer.ec1.payment.factory;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.ApproveRes;
import com.plateer.ec1.payment.vo.CancelReq;
import com.plateer.ec1.payment.vo.PayApproveReq;
import com.plateer.ec1.payment.vo.PayCompleteReq;

public interface Payment {

    ApproveRes approvePay(PayApproveReq payInfo);

    void completePay(PayCompleteReq payInfo);

    void cancelData(CancelReq cancelReq);

    void cancelPay(CancelReq cancelReq);

    void netCancel(CancelReq cancelReq);

    PaymentType getType();
}
