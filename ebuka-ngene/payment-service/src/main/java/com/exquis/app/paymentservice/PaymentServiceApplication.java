package com.exquis.app.paymentservice;

import com.exquis.app.paymentservice.dto.RaveCheckTransactionResponseDto;
import com.exquis.app.paymentservice.service.contract.PaymentServiceContract;
import com.exquis.app.paymentservice.service.contract.RaveServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.exquis.app.paymentservice"})
public class PaymentServiceApplication extends SpringBootServletInitializer implements CommandLineRunner {
	@Autowired private RaveServiceContract raveService;
	@Autowired private PaymentServiceContract paymentService;

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testRaveTransaction();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	private void testRaveTransaction()
	{
		//RaveCheckTransactionResponseDto responseObject = raveService.checkTransactionStatus("1621349886083");
		paymentService.paymentResponse("successful", "1637733307304", "2648054");
	}

}
