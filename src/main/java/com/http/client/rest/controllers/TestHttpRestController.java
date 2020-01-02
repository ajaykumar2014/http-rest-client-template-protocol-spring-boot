package com.http.client.rest.controllers;

import com.http.client.rest.template.client.HttpRestTemplateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestHttpRestController extends BaseController {

    @Autowired
    private HttpRestTemplateClient restTemplateClient;



    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity testGet(@RequestParam("url") String url){
        return getHttpResponse(restTemplateClient.getRequest(url));
    }
}
