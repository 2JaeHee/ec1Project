package com.plateer.ec1.payment.factory.impl;

import com.plateer.ec1.common.code.order.OPT0009Enum;
import com.plateer.ec1.common.code.order.OPT0010Enum;
import com.plateer.ec1.common.code.promotion.PRM0011Enum;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.Payment;
import com.plateer.ec1.payment.mapper.PaymentMapper;
import com.plateer.ec1.payment.vo.*;
import com.plateer.ec1.promotion.service.PointService;
import com.plateer.ec1.promotion.vo.req.PointReqVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Point implements Payment {
    private final PointService pointService;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentType getType() {
        return PaymentType.POINT;
    }

    @Override
    public ApproveRes approvePay(PayApproveReq payInfo) {
        OrderPayInfoRes orderPayInfo = getOrderPayInfo(OrderPayInfoReq.point(payInfo.getOrdNo()));
        PointReqVo pointReqVo = PointReqVo.builder()
                .mbrNo(orderPayInfo.getMbrNo())
                .pntBlc(payInfo.getPrc())
                .ordNo(payInfo.getOrdNo())
                .payNo(orderPayInfo.getPayNo())
                .build();

        try {
            pointService.savePointInfo(pointReqVo, PRM0011Enum.USE);
        } catch (Exception e) {
            return ApproveRes.builder().resultCd("N").build();
        }
        //TODO 포인트 사용 시 결제 insert 해주기 !
        return ApproveRes.builder().resultCd("Y").build();
    }

    @Override
    public void completePay(PayCompleteReq payInfo) {
        log.info("== 포인트에서는 사용 안함");
    }

    @Override
    public void cancelData(CancelReq cancelReq) {
        log.info("== 포인트에서는 사용 안함");
    }

    @Override
    public void cancelPay(CancelReq cancelReq) {
        OrderPayInfoRes orderPayInfo = getOrderPayInfo(OrderPayInfoReq.point(cancelReq.getOrdNo()));

        PointReqVo pointReqVo = PointReqVo.builder()
                .mbrNo(orderPayInfo.getMbrNo())
                .pntBlc(cancelReq.getCancelAmt())
                .ordNo(cancelReq.getOrdNo())
                .payNo(orderPayInfo.getPayNo())
                .build();

        pointService.savePointInfo(pointReqVo, PRM0011Enum.CANCEL);
        //TODO 포인트 취소 시 결제 insert 해주기 !
    }

    /**
     * 주문에 해당하는 결제 정보 조회
     */
    private OrderPayInfoRes getOrderPayInfo(OrderPayInfoReq orderPayInfoReq) {
        return paymentMapper.getOrderPayInfo(orderPayInfoReq);
    }
}
