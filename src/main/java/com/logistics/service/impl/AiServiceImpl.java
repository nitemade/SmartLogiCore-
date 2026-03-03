package com.logistics.service.impl;

import com.logistics.service.AiService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class AiServiceImpl implements AiService {

    private static final String API_URL = "https://api.example.com/ai";
    private static final String API_KEY = "your-api-key";

    @Override
    public String generateReport(String data) {
        return callAiApi("generate-report", data);
    }

    @Override
    public String analyzeData(String data) {
        return callAiApi("analyze-data", data);
    }

    @Override
    public String getRecommendations(String data) {
        return callAiApi("get-recommendations", data);
    }

    private String callAiApi(String endpoint, String data) {
        try {
            URL url = new URL(API_URL + "/" + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setDoOutput(true);

            String jsonInputString = "{\"data\": \"" + data + "\"}";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error calling AI API: " + e.getMessage();
        }
    }
}