package com.tw.apistackbase.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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


    @Test
    public void should_return_status_isOK_and_contentList_when_given_request_page_pagesize() throws Exception {
        mockMvc.perform(get("/employees/pages")
                .param("page","2")
                .param("pageSize","2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"employeeID\":3,\"employeeName\":\"xiaona\",\"gender\":\"female\",\"age\":18,\"compainesID\":3},{\"employeeID\":4,\"employeeName\":\"xiaoyang\",\"gender\":\"female\",\"age\":18,\"compainesID\":3}]"));
    }

    @Test
    public void should_return_status_isNotContent_and_contentList_when_given_request_page_pagesize() throws Exception {
        mockMvc.perform(get("/employees/pages")
                .param("page","3")
                .param("pageSize","2"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_return_status_isOK_and_content_when_given_a_request_gender() throws Exception {
        mockMvc.perform(get("/employees").param("gender", "female"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"employeeID\":3,\"employeeName\":\"xiaona\",\"gender\":\"female\",\"age\":18,\"compainesID\":3},{\"employeeID\":4,\"employeeName\":\"xiaoyang\",\"gender\":\"female\",\"age\":18,\"compainesID\":3}]"));
    }

    @Test
    public void should_return_status_isCreated_and_contentList_when_given_post_request() throws Exception {
        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content("{\"employeeID\":7,\"employeeName\":\"xiaoya\",\"gender\":\"female\",\"age\":18,\"compainesID\":3}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void should_return_status_isOK_and_contentList_when_given_put_request() throws Exception {
        mockMvc.perform(put("/employees").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content("{\"employeeID\":7,\"employeeName\":\"xiaoyaa\",\"gender\":\"female\",\"age\":18,\"compainesID\":3}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_status_isOK_and_contentList_when_given_delete_request() throws Exception {
        mockMvc.perform(delete("/employees/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
