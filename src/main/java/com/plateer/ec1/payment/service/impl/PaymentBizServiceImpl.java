package com.plateer.ec1.payment.service.impl;

import com.plateer.ec1.common.model.order.OpPayInfo;
import com.plateer.ec1.payment.mapper.PaymentMapper;
import com.plateer.ec1.payment.mapper.PaymentTrxMapper;
import com.plateer.ec1.payment.service.PaymentBizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentBizServiceImpl implements PaymentBizService {
    private final PaymentMapper paymentMapper;
    private final PaymentTrxMapper paymentTrxMapper;

    @Override
    public void savePayInfo(OpPayInfo basePayInfo) {
        paymentTrxMapper.savePayInfo(basePayInfo);
    }

    @Override
    public void modifyPayInfo(OpPayInfo opPayInfo) {
        paymentTrxMapper.modifyPayInfo(opPayInfo);
    }

    @Override
    public OpPayInfo getPayInfo(String trsnId) {
        return paymentMapper.getPayInfo(trsnId);
    }
}
