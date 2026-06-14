package com.example.parking.payment.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HexFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MomoService {

    @Value("${momo.partner-code}")
    private String partnerCode;

    @Value("${momo.access-key}")
    private String accessKey;

    @Value("${momo.secret-key}")
    private String secretKey;

    @Value("${momo.endpoint}")
    private String endpoint;

    @Value("${momo.redirect-url}")
    private String redirectUrl;

    @Value("${momo.notify-url}")
    private String notifyUrl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String createPaymentUrl(Long sessionId, long amount) throws Exception {
        String requestId = UUID.randomUUID().toString();
        String orderId = sessionId + "_" + System.currentTimeMillis(); // unique mỗi lần
        String orderInfo = "Thanh toan gui xe #" + sessionId;
        String extraData = "";

        String rawSignature = "accessKey=" + accessKey
                + "&amount=" + amount
                + "&extraData=" + extraData
                + "&ipnUrl=" + notifyUrl
                + "&orderId=" + orderId
                + "&orderInfo=" + orderInfo
                + "&partnerCode=" + partnerCode
                + "&redirectUrl=" + redirectUrl
                + "&requestId=" + requestId
                + "&requestType=payWithMethod";

        String signature = hmacSHA256(rawSignature, secretKey);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("partnerCode", partnerCode);
        body.put("accessKey", accessKey);
        body.put("requestId", requestId);
        body.put("amount", amount);
        body.put("orderId", orderId);
        body.put("orderInfo", orderInfo);
        body.put("redirectUrl", redirectUrl);
        body.put("ipnUrl", notifyUrl);
        body.put("extraData", extraData);
        body.put("requestType", "payWithMethod");
        body.put("signature", signature);
        body.put("lang", "vi");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        objectMapper.writeValueAsString(body)))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("MoMo response: " + response.body());

        Map<?, ?> result = objectMapper.readValue(response.body(), Map.class);
        String payUrl = (String) result.get("payUrl");
        if (payUrl == null) {
            throw new RuntimeException("MoMo error: " + result.get("message")
                    + " - resultCode: " + result.get("resultCode"));
        }
        return payUrl;
    }

    public boolean verifyCallback(Map<String, String> params) throws Exception {
        String rawSignature = "accessKey=" + accessKey
                + "&amount=" + params.get("amount")
                + "&extraData=" + params.get("extraData")
                + "&message=" + params.get("message")
                + "&orderId=" + params.get("orderId")
                + "&orderInfo=" + params.get("orderInfo")
                + "&orderType=" + params.get("orderType")
                + "&partnerCode=" + params.get("partnerCode")
                + "&payType=" + params.get("payType")
                + "&requestId=" + params.get("requestId")
                + "&responseTime=" + params.get("responseTime")
                + "&resultCode=" + params.get("resultCode")
                + "&transId=" + params.get("transId");

        String signature = hmacSHA256(rawSignature, secretKey);
        return signature.equals(params.get("signature"));
    }

    private String hmacSHA256(String data, String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));
        return HexFormat.of().formatHex(mac.doFinal(data.getBytes()));
    }
}