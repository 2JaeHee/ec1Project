package com.plateer.ec1.payment.vo.inicis;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class InicisCompleteReq {
    private String no_tid;
    private String no_oid;
    private String id_merchant;
    private String cd_bank;
    private String cd_deal;
    private String dt_trans;
    private String tm_trans;
    private String no_msgseq;
    private String cd_joinorg;
    private String dt_transbase;
    private String no_transseq;
    private String type_msg;
    private String cl_trans;
    private String cl_close;
    private String cl_kor;
    private String no_msgmanage;
    private String no_vacct;
    private String amt_input;
    private String amt_check;
    private String nm_inputbank;
    private String nm_input;
    private String dt_inputstd;
    private String dt_calculstd;
    private String flg_close;
    private String no_req_tid;
}
