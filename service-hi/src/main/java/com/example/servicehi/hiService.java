package com.example.servicehi;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class hiService {
    @Value("${server.port}")
    String port;
    @Value("${spring.application.name}")
    String servicename;
    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiPort(@RequestParam String name){
        return "hi "+name+",  i am from port: "+port+"   and from service: "+servicename;
    }

    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
