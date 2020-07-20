package com.thedrinkwholesale.dws.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    MultiValueMap<String,String> multiValueMap = new LinkedMultiValueMap<>();


    @Test
    public void indexTest() throws Exception{
        mockMvc.perform(post("/user/ckuser").param("userid","dsfdsf"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void loginTest() throws Exception{

        mockMvc.perform(post("/user/login").contentType("application/x-www-form-urlencoded").content("userid=123&password=123"))
                .andDo(print())
                .andExpect(status().isOk());


    }



}