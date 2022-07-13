package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.factory.Payment;
import com.plateer.ec1.payment.factory.PaymentFactory;
import com.plateer.ec1.payment.vo.ApproveResVO;
import com.plateer.ec1.payment.vo.CancelReq;
import com.plateer.ec1.payment.vo.PayInfo;
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
    public ApproveResVO approve(PayInfo payInfo) {
        Payment factory = paymentFactory.getPaymentFactory(payInfo.getPaymentType());
        return factory.approvePay(payInfo);
    }

    /**
     * 결제 완료
     * @param payInfo
     */
    @Override
    public void completePay(PayInfo payInfo) {
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

    /**
     * 결제망취소
     * @param cancelReq
     */
    @Override
    public void netCancel(CancelReq cancelReq) {
        Payment factory = paymentFactory.getPaymentFactory(cancelReq.getPaymentType());
        factory.netCancel(cancelReq);
    }


}
