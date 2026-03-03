package com.logistics.controller;

import com.logistics.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Autowired
    private AiService aiService;

    @PostMapping("/generate-report")
    public String generateReport(@RequestBody String data) {
        return aiService.generateReport(data);
    }

    @PostMapping("/analyze-data")
    public String analyzeData(@RequestBody String data) {
        return aiService.analyzeData(data);
    }

    @PostMapping("/get-recommendations")
    public String getRecommendations(@RequestBody String data) {
        return aiService.getRecommendations(data);
    }
}