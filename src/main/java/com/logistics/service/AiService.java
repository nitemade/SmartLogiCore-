package com.logistics.service;

public interface AiService {
    String generateReport(String data);
    String analyzeData(String data);
    String getRecommendations(String data);
}