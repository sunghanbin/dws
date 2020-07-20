package com.thedrinkwholesale.dws.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomUserDetailControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void indexTest() throws Exception{
        mockMvc.perform(get("/login"))
                    .andDo(print())
                    .andExpect(status().isOk());
    }

    @Test
    public void index() throws Exception{
        mockMvc.perform(post("/user/loginForm").param("userid","gksqlsdddd").param("password","123"))
                .andDo(print())
                .andExpect(status().isOk())
                ;
    }

}