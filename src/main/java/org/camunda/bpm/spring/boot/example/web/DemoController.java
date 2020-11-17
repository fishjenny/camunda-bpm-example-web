package org.camunda.bpm.spring.boot.example.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("demo/sayHello")
    public String sayhello(){
        return "HelloWorld";
    }
}
