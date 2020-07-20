package com.thedrinkwholesale.dws.controller;


import com.thedrinkwholesale.dws.JwtToken.JwtTokenProvider;
import com.thedrinkwholesale.dws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @GetMapping("/ckuser")
    public boolean ckuser(@RequestParam String userid){

        return userService.sameIdsh(userid);
    }


// 회원가입
//    * MultiValueMap *
    @PostMapping("/signup")
    public String signupForm(@RequestBody MultiValueMap<String,String> user) {
        System.out.println(user);
        Map<String,String> mapUser = user.toSingleValueMap();


        return userService.signUpUser(mapUser);
    }



    @PostMapping("/Mypage")
    public String getLogin(HttpServletRequest req) {







        return "home";
    }

    @PostMapping("/login")
    public Map<String, Object> loginForm(@RequestBody MultiValueMap<String,String> user, HttpServletResponse res) {



        Map<String,String> mapUser = user.toSingleValueMap();


        return userService.loginUser(mapUser,res);
    }

}
