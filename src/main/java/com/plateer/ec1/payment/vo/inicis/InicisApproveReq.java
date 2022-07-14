package com.plateer.ec1.payment.vo.inicis;

import com.plateer.ec1.common.utils.AesAnDesUtil;
import com.plateer.ec1.order.enums.inicis.InicisPayType;
import com.plateer.ec1.order.enums.inicis.InicisPaymethod;
import com.plateer.ec1.payment.vo.PayApproveReq;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
public class InicisApproveReq {
    private static String apikey = "ItEQKi3rY7uvDS8l";

    private static final int INPUT_HOUR = 24;
    private static final String CURRENCY_CODE = "WON";

    public static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    @NotEmpty
    private String type;        //가상계좌채번 type : Pay 고정
    @NotEmpty
    private String paymethod;   //가상계좌유형 : Vacct 고정
    @NotEmpty
    private String timestamp;    //전문생성시간 [YYYYMMDDhhmmss]
    @NotEmpty
    private String clientIp;        //가맹점 요청 서버IP
    @NotEmpty
    private String mid;             //상점아이디
    @NotEmpty
    private String url;             //가맹점 URL
    @NotEmpty
    private String moid;            //가맹점주문번호
    @NotEmpty
    private String goodName;         //상품명
    @NotEmpty
    private String buyerName;       //구매자명
    @NotEmpty
    private String buyerEmail;      //* "@", "." 외 특수문자 입력불가
    private String buyerTel;        //구매자 휴대폰번호
    @NotNull
    private Long price;             //거래금액

    private String currency;    //통화코드 [WON,USD]

    @NotEmpty
    private String bankCode;    //은행코드
    @NotEmpty
    private String dtInput;     //입금예정일자 [YYYYMMDD]
    @NotEmpty
    private String tmInput;     //입금예정시간 [hhmm]
    @NotEmpty
    private String nmInput;     //입금자명

    private String flgCash;     //현금영수증 발행여부 ["0":미발행, "1":소득공제 발행, "2":지출증빙]
    private String cashRegNo;   //현금영수증 발행정보 (주민번호, 휴대폰번호, 사업장등록번호 등)
    private String vacctType;   //타입 ["3" 과오납체크] (과오납체크의 경우만 세팅)
    private String vacct;       //벌크가상계좌번호 (과오납체크의 경우만 세팅)

    @NotEmpty
    private String hashData;    //전문위변조 HASH (hash(INIAPIKey+type+paymethod+timestamp+clientIp+mid+moid+price)))

    public static InicisApproveReq of(@NonNull PayApproveReq payInfo) {
        InicisApproveReq inicisApproveReq = InicisApproveReq.builder()
                .type(InicisPayType.PAY.getCode())
                .paymethod(InicisPaymethod.VACCT.getCode())
                .timestamp(LocalDateTime.now().format(df))
                .clientIp(payInfo.getClientIp())
                .mid(payInfo.getMid())
                .url(payInfo.getUrl())
                .moid(payInfo.getOrdNo())
                .goodName(payInfo.getGoodsNm())
                .buyerName(payInfo.getMbrNm())
                .buyerEmail(payInfo.getMbrEmail())
                .buyerTel(payInfo.getMbrPhoneNo())
                .price(payInfo.getPrc())
                .currency(CURRENCY_CODE)    //WON, USD
                .bankCode(payInfo.getBankCode().getCode())
                .nmInput(payInfo.getMbrNm())
                .build();
        //입금시간 = 현재 + 24시간
        inicisApproveReq.setInputTime();
        //hash
        inicisApproveReq.setHashData();
        return inicisApproveReq;
    }

    private void setInputTime() {
        LocalDateTime getTimestamp = LocalDateTime.parse(this.timestamp, df);
        LocalDateTime inputTime = getTimestamp.plusHours(INPUT_HOUR);
        this.dtInput = inputTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.tmInput = inputTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    public void setHashData() {
        String hashData = apikey +
                this.type +
                this.paymethod +
                this.timestamp +
                this.clientIp +
                this.mid +
                this.moid +
                this.price;
        this.hashData = AesAnDesUtil.encodeSha(hashData);
    }
}
