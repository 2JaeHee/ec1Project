package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.vo.ApproveResVO;
import com.plateer.ec1.payment.vo.CancelReq;
import com.plateer.ec1.payment.vo.PayInfo;

public interface PaymentService {
    ApproveResVO approve(PayInfo payInfo);

    void cancel(CancelReq cancelReq);

    void netCancel(CancelReq cancelReq);
}

