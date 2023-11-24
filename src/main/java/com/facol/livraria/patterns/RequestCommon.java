package com.facol.livraria.patterns;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RequestCommon {

    public Object get(String url, HttpMethod method, HttpEntity<?> entity, Class<?> object){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<?> exchange = restTemplate.exchange(url, method, entity, object);
        return exchange.getBody();
    }
}
