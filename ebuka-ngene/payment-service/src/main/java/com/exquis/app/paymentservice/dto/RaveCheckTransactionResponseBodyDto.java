package com.exquis.app.paymentservice.dto;

import lombok.Data;

/**
 *
 * @author chukwuebuka
 */
@Data
public class RaveCheckTransactionResponseBodyDto implements dto {
    private Long txid;
    private String txref;
    private String flwref;
    private String orderref;
    private String raveref;
    private String devicefingerprint;
    private String cycle;
    private String currency;
    private Double merchantfee;
    private Double merchantbearsfee;
    private String chargecode;
    private String chargemessage;
    private String authmodel;
    private String ip;
    private String narration;
    private String status;
    private String vbvmessage;
    private String paymenttype;
    private String paymentid;
    private String created;
    private String customerid;
    private String custname;
    private String custemail;
    private Long accountid;
    private String acctbusinessname;
    private String acctcontactperson;
    private String acctcountry;
    private Double amount;
    private Double chargedamount;
    private Double appfee;
    private Double amountsettledforthistransaction;
    private Object card;
    private Object meta;
}
