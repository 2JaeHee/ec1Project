package com.plateer.ec1.order.service;

import com.plateer.ec1.order.strategy.AfterStrategy;
import com.plateer.ec1.order.strategy.DataStrategy;
import com.plateer.ec1.order.validator.OrderCommonValidators;
import com.plateer.ec1.order.validator.OrderValidator;
import com.plateer.ec1.order.vo.OrderDto;
import com.plateer.ec1.order.vo.OrderProductView;
import com.plateer.ec1.order.vo.OrderRequest;
import com.plateer.ec1.order.vo.OrderValidationDto;
import com.plateer.ec1.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderContext {
    private final PaymentService paymentService;

    public OrderDto excute(DataStrategy dataStrategy, AfterStrategy afterStrategy, OrderRequest orderRequest) {
        OrderDto orderDto = new OrderDto();
        OrderValidationDto orderValidationDto = new OrderValidationDto();
        //모니터링 로그생성 (주문클레임모니터링 T insert)

        try {

            //주문 파라미터 유효성검사
            // 1. 공통유효성 -> 재고관리 시 재고체크, 상품 판매상태 체크, 상품 최대/최소 구매제한 체크
            // 2. 주문기본 유효성 -> 업체 상태체크, 쿠폰적용가능여부, 배송지 유무 체크, 시스템별 가능한 주문유형체크, 시스템별 파라미터
            // 3. 주문유형별 유효성 -> 주문유형별 필수 데이터, 주문유형별 상품 확인
            OrderValidator.get(orderRequest).test(orderValidationDto);
            //주문 데이터 생성/등록 (혜택 배분 : 총혜택금액 * 상품가격 / 총상품가격)
            orderDto = dataStrategy.create(orderRequest, new OrderProductView());

            insertOrderData(orderDto);
            //결제
            paymentService.approve(orderRequest.getPayInfo());
            //주문 금액 검증 sum(주문상품금액) + sum(배송비용) - sum(혜택) = sum(결제)
            OrderCommonValidators.isAmountVerification.test(orderValidationDto);
            //재고차감 (업무 제외)
            //후처리 알림톡 등등
            afterStrategy.call(orderRequest, orderDto);
        } catch (Exception e) {

        } finally {
            //모니터링 로그 결과업데이트 (주문클레임모니터링 T update)
        }
        return orderDto;
    }

    private void insertOrderData(OrderDto orderDto) {
        log.info("OrderContext.insertOrderData - 주문데이터 insert");
    }
}
