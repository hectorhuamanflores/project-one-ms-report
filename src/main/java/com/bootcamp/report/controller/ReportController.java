package com.bootcamp.report.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.report.dto.BalanceResponse;
import com.bootcamp.report.service.ReportService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
	
	private final ReportService reportService;
	
	@GetMapping("/{numDoc}")
    public Flux<BalanceResponse>getAllReportBalance(@PathVariable String numDoc) {
        Flux<BalanceResponse> list=this.reportService.getReportBalance(Integer.parseInt(numDoc));
        return  list;
    }

}
