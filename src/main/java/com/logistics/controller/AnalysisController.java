package com.logistics.controller;

import com.logistics.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/order")
    public Map<String, Object> getOrderAnalysis() {
        return analysisService.getOrderAnalysis();
    }

    @GetMapping("/inventory")
    public Map<String, Object> getInventoryAnalysis() {
        return analysisService.getInventoryAnalysis();
    }

    @GetMapping("/sales")
    public Map<String, Object> getSalesAnalysis() {
        return analysisService.getSalesAnalysis();
    }

    @GetMapping("/report")
    public Map<String, Object> generateReport() {
        return analysisService.generateReport();
    }
}