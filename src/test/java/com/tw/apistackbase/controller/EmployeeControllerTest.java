package com.tw.apistackbase.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_status_isOK_and_content_when_given_a_request() throws Exception {
        mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"employeeID\":1,\"employeeName\":\"xiaohuang\",\"gender\":\"male\",\"age\":18,\"compainesID\":1}]"));
    }

    @Test
    public void should_return_status_isOK_and_content_when_given_a_request_employeeID() throws Exception {
        mockMvc.perform(get("/employees/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"employeeID\":1,\"employeeName\":\"xiaohuang\",\"gender\":\"male\",\"age\":18,\"compainesID\":1}"));
    }

    @Test
    public void should_return_status_isNOCONTENT_and_content_when_given_a_request_employeeID() throws Exception {
        mockMvc.perform(get("/employees/6"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}