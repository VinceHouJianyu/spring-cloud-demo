package com.example.configclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

    @Value("${democonfigclient.message}")
    String message;

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi() {
        return message;
    }



    @Autowired
    HelloService helloService;
    @RequestMapping(value = "/hi/ribbon")
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiRibbon(@RequestParam String name){
        return helloService.hiService(name);
    }


    @Autowired
    SchedualServiceHi schedualServiceHi;
    @RequestMapping(value = "/hi/feign",method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "hiError")
    public String sayHi(@RequestParam String name){
        return schedualServiceHi.sayHiFromClientOne(name);
    }

    public String hiError() {
        return "hi, sorry,error---config-client!";
    }
    public String hiError(String name) {
        return "hi"+name+", sorry,error---config-client!";
    }
}
