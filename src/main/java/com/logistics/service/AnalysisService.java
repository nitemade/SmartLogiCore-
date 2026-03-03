package com.logistics.service;

import java.util.Map;

public interface AnalysisService {
    Map<String, Object> getOrderAnalysis();
    Map<String, Object> getInventoryAnalysis();
    Map<String, Object> getSalesAnalysis();
    Map<String, Object> generateReport();
}