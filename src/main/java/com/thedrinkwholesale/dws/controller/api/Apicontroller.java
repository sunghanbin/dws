package com.thedrinkwholesale.dws.controller.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class Apicontroller {


    @GetMapping("/hello")
    public String react() {
        return "안녕하세요. 현재 서버시간은" + new Date() + "입니다. \n";
    }

    @GetMapping("/hi")
    public String react2() {
        return "안녕. 현재 시간은" + new Date() + "\n";
    }




}
