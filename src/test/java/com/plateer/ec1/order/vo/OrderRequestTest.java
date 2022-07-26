package com.plateer.ec1.order.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateer.ec1.common.model.order.OpOrdBase;
import com.plateer.ec1.common.model.order.OpOrdClmMntLog;
import com.plateer.ec1.order.mapper.OrderClaimLogTrxMapper;
import com.plateer.ec1.order.mapper.OrderMapper;
import com.plateer.ec1.order.vo.ordersheet.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class OrderRequestTest {
    private Validator validator;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderClaimLogTrxMapper orderClaimLogTrxMapper;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void parameterEmptyCheck() throws JsonProcessingException, JSONException {
        OrderReq orderReq = setParameter();

        String ordNo = orderMapper.getOrdNo();
        orderReq.setOrdNo(ordNo);
        ObjectMapper mapper = new ObjectMapper();
        String orderReqJsonString = mapper.writeValueAsString(orderReq);
        orderClaimLogTrxMapper.saveCreateLog(OpOrdClmMntLog.createOf(orderReq.getOrderNo(), orderReqJsonString));
//        Set<ConstraintViolation<FranchiseeReq>> violations = validator.validate(franchiseeReq);
//        assertTrue(violations.isEmpty());

//        Assertions.assertTrue();
    }

    private OrderReq setParameter() {
        //주문기본
        OpOrdBase opOrdBase = OpOrdBase.builder().mbrNo("M001").ordTpCd("10").ordSysCcd("10")
                .ordNm("홍길동").ordSellNo("01012345678").ordAddr("송파구백제고분로").ordAddrDtl("에이치비지니스파크6층").build();
        //주문상품
        List<OrdGoodsInfoReq> ordGoodsInfoReqList = new ArrayList<>();
        List<OrdBnfInfoReq> bnfInfoReqs = new ArrayList<>();
        bnfInfoReqs.add(OrdBnfInfoReq.builder().prmNo(1L).cpnKindCd("10").cpnIssNo(1L).degrCcd("1").build());
        ordGoodsInfoReqList.add(
                OrdGoodsInfoReq.builder().ordGoodsNo("P001").ordItemNo("1").goodsSellTpCd("10").ordCnt(1)
                        .ordBnfInfoReqList(bnfInfoReqs).build());
        //주문혜택
        List<OrdBnfInfoReq> ordBnfInfoReqList = new ArrayList<>();
        List<OrdGoodsInfoReq> ordGoodsInfoReqs = new ArrayList<>();
        ordGoodsInfoReqs.add(OrdGoodsInfoReq.builder().ordGoodsNo("P001").ordItemNo("1").build());
        ordBnfInfoReqList.add(OrdBnfInfoReq.builder().prmNo(1L).cpnIssNo(1L).cpnKindCd("30").degrCcd("3").ordGoodsInfoReqs(ordGoodsInfoReqs).build());
        //배송지
        List<OrdDvpAreaInfoReq> ordDvpAreaInfoReqList = new ArrayList<>();
        List<SumDvpAreaInfoReq> sumDvpAreaInfoReqs = new ArrayList<>();
        List<OrdGoodsInfoReq> goodsInfoReqList = new ArrayList<>();
        goodsInfoReqList.add(OrdGoodsInfoReq.builder().ordGoodsNo("P001").ordItemNo("1").build());
        sumDvpAreaInfoReqs.add(SumDvpAreaInfoReq.builder().sumDvpSeq(1).ordGoodsInfoReqList(goodsInfoReqList).build());
        ordDvpAreaInfoReqList.add(OrdDvpAreaInfoReq.builder().dvpSeq(1).rmtiNm("홍길동").rmtiHpNo("01011112222")
                .rmtiAddr("송파구백제고분로r").rmtiAddrDtl("에이치비지니스파크6층r").sumDvpAreaInfoReqList(sumDvpAreaInfoReqs).build());
        //결제
        OrdPayInfoReq ordPayInfoReq = OrdPayInfoReq.builder().payAmt(29000L).build();

        return OrderReq.builder()
                .orderNo("O202207250001")
                .opOrdBase(opOrdBase)
                .ordGoodsInfoReqList(ordGoodsInfoReqList)
                .ordBnfInfoReqList(ordBnfInfoReqList)
                .ordDvpAreaInfoReqList(ordDvpAreaInfoReqList)
                .ordPayInfoReq(ordPayInfoReq)
                .build();
    }
}