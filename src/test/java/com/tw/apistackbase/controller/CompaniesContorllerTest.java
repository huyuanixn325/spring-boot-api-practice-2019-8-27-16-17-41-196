package com.tw.apistackbase.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompaniesContorllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_return_status_isOK_and_content_when_given_a_request_list() throws Exception {
        mockMvc.perform(get("/companies"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"companiesID\":1,\"companiesName\":\"百度\"},{\"companiesID\":2,\"companiesName\":\"腾讯\"}]"));
    }

    @Test
    public void should_return_status_isOK_and_content_when_given_a_request_companiesID() throws Exception {
        mockMvc.perform(get("/companies/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"companiesID\":1,\"companiesName\":\"百度\"}"));
    }

    
}