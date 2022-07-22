package com.plateer.ec1.payment.service.impl;

import com.plateer.ec1.payment.factory.Payment;
import com.plateer.ec1.payment.factory.PaymentFactory;
import com.plateer.ec1.payment.service.PaymentService;
import com.plateer.ec1.payment.vo.ApproveRes;
import com.plateer.ec1.payment.vo.CancelReq;
import com.plateer.ec1.payment.vo.PayApproveReq;
import com.plateer.ec1.payment.vo.PayCompleteReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentFactory paymentFactory;

    /**
     * 결제 승인
     * @param payInfo
     * @return ApproveResVO
     */
    @Override
    public ApproveRes approve(PayApproveReq payInfo) {
        Payment factory = paymentFactory.getPaymentFactory(payInfo.getPaymentType());
        return factory.approvePay(payInfo);
    }

    /**
     * 결제 완료
     * @param payInfo
     */
    @Override
    public void completePay(PayCompleteReq payInfo) {
        Payment factory = paymentFactory.getPaymentFactory(payInfo.getPaymentType());
        factory.completePay(payInfo);
    }

    /**
     * 결제취소
     * @param cancelReq
     */
    @Override
    public void cancel(CancelReq cancelReq) {
        Payment factory = paymentFactory.getPaymentFactory(cancelReq.getPaymentType());
        factory.cancelPay(cancelReq);
    }


}
