package com.bootcamp.report.service.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bootcamp.report.dto.BalanceResponse;
import com.bootcamp.report.dto.Credit;
import com.bootcamp.report.dto.CreditBalance;
import com.bootcamp.report.dto.CreditByNumDocRequest;
import com.bootcamp.report.service.ReportService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService{
    
	@Autowired
    private  WebClient.Builder builder;
	
	private Function<Flux<Credit>, Flux<CreditBalance>> convertionCredit = (credit) -> credit.flatMap(o ->{
		return Flux.just(CreditBalance
		  .builder()
		  .balanceP(o.getConsumeCredit()/30)
		  .idCredit(o.getId())
		  .build());
	});
	
	

	
	private Function<CreditByNumDocRequest,Flux<Credit>> creditNumberDocument = (objeto) -> builder
			.baseUrl("http://ms-credit")
		    .build()
			.post()
			.uri("/Credit/numberDocument/")
			.body(Flux.just(objeto), Credit.class)
			.retrieve()
			.bodyToFlux(Credit.class);
	
	@Override
	public Flux<BalanceResponse> getReportBalance(Integer documentNumber) {
		CreditByNumDocRequest e = CreditByNumDocRequest.builder()
				.documentNumber(documentNumber)
				.build();
		log.info("INICIO creditBalance");
		Flux<Credit> creditBalance = creditNumberDocument.apply(e);
		Flux<CreditBalance> creditBalanceCon = convertionCredit.apply(creditBalance);
		log.info("FIN creditBalance");
				                
		log.info("FIN creditBalanceCon");
		
		return creditBalanceCon.flatMap( r -> {
			log.info("FIN r"+r.getIdCredit());
			log.info("FIN r"+r.getBalanceP());
			return Flux.just(BalanceResponse.builder()
					.creditBalance(null)
					.accountBalance(null)
					.build());
		});
	}

}
