package com.wty.network.restTemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 *
 * @author wty
 * @date 2021/1/3 19:53
 */
public class Demo {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Demo demo = new Demo();
        demo.get(restTemplate);
    }

    public void get(RestTemplate restTemplate){
        ResponseEntity<Object> forEntity = restTemplate.getForEntity("www.baidu.com", null);
    }
}
