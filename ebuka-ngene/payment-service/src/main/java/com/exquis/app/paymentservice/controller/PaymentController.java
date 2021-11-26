package com.exquis.app.paymentservice.controller;

import com.exquis.app.paymentservice.service.contract.PaymentServiceContract;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

/**
 *
 * @author chukwuebuka
 */
@CrossOrigin
@RestController
@RequestMapping("payment/")
@Api(value = "MoneyPay", description = "Payment Resource")
public class PaymentController {
    @Autowired private PaymentServiceContract paymentService;

    @ApiOperation("Call back url for payment gateway")
    @RequestMapping(value = "/rave/response", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> callBackURL(@QueryParam("status") String status,
                                         @QueryParam("tx_ref") String tx_ref,
                                         @QueryParam("transaction_id") String transaction_id) throws Exception
    {
        paymentService.paymentResponse(status, tx_ref, transaction_id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
