package com.thedrinkwholesale.dws.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void buyersearchT() throws Exception{
        mockMvc.perform(get("/buyerall").param("page","0").param("size","10"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void cityCodeT() throws Exception{
        mockMvc.perform(get("/citycode"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void siguCodeT() throws Exception{
        mockMvc.perform(get("/sigucode"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}