package com.exquis.app.paymentservice.service;

import com.exquis.app.paymentservice.dto.RaveCheckTransactionResponseDto;
import com.exquis.app.paymentservice.exception.HttpBadRequestException;
import com.exquis.app.paymentservice.service.contract.RaveServiceContract;
import com.exquis.app.paymentservice.utility.Helper;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author chukwuebuka
 */
@Slf4j
@Service
public class RaveService implements RaveServiceContract {
    @Autowired
    private RestTemplate restTemplate;

    @SneakyThrows
    @Override
    public RaveCheckTransactionResponseDto checkTransactionStatus(String transactionRef) {
        if(transactionRef == null) {
            log.warn("Transaction reference is required by rave to check this transaction");
            throw new HttpBadRequestException("Transaction reference is required by rave to check this transaction");
        }

        long startTime = System.currentTimeMillis();

        //FLWPUBK_TEST-5a4309c1b7dbf700e63dc4d676f438ec-X
        String public_key = "FLWPUBK_TEST-5a4309c1b7dbf700e63dc4d676f438ec-X";
        String secret = "FLWSECK_TEST-117dd628266d57c372cb3c3cd193fe2f-X";
        String URL = "https://api.ravepay.co/flwv3-pug/getpaidx/api/v2/verify";
        String secret_test = "FLWSECK_TEST-117dd628266d57c372cb3c3cd193fe2f-X";

        log.info("request to check transaction {} at {} ", URL, startTime);

        JSONObject data = new JSONObject();
        data.put("txref", transactionRef);
        data.put("SECKEY", secret_test);

        HttpEntity<String> request = new HttpEntity<String>(data.toString(), Helper.getHeaders(null, null, null));

        try{
            ResponseEntity<RaveCheckTransactionResponseDto> response  =   restTemplate.exchange(URL, HttpMethod.POST, request, RaveCheckTransactionResponseDto.class);

            if(response.getBody() != null)
            {
                log.info("check transaction went successful");
                log.info("<return> Rave response, elapsed time {}", System.currentTimeMillis() - startTime);

                return response.getBody();
            }
            else {
                throw new Exception("No response from payment gateway");
            }
        }
        catch(HttpClientErrorException ex){
            log.error(ex.getMessage());
        }

        return null;
    }
}
