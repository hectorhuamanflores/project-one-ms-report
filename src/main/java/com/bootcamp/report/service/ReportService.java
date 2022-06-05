package com.bootcamp.report.service;

import com.bootcamp.report.dto.BalanceResponse;

import reactor.core.publisher.Flux;

public interface ReportService {
	
	public Flux<BalanceResponse> getReportBalance(Integer documentNumber);
}
