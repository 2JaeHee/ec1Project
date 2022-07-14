package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.vo.ApproveResVO;
import com.plateer.ec1.payment.vo.CancelReq;
import com.plateer.ec1.payment.vo.PayApproveReq;
import com.plateer.ec1.payment.vo.PayCompleteReq;

public interface PaymentService {
    ApproveResVO approve(PayApproveReq payInfo);

    void completePay(PayCompleteReq payInfo);

    void cancel(CancelReq cancelReq);

    void netCancel(CancelReq cancelReq);
}

