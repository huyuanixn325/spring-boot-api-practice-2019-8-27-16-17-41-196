package com.tw.apistackbase.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
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

    @Test
    public void should_return_status_isOK_and_contentList_when_given_request_page_pagesize() throws Exception {
        mockMvc.perform(get("/companies/pages")
                .param("page","1")
                .param("pageSize","3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"companiesID\":1,\"companiesName\":\"百度\"},{\"companiesID\":2,\"companiesName\":\"腾讯\"},{\"companiesID\":3,\"companiesName\":\"阿里\"}]"));
    }

    @Test
    public void should_return_status_isNotContent_and_contentList_when_given_request_page_pagesize() throws Exception {
        mockMvc.perform(get("/companies/pages")
                .param("page","3")
                .param("pageSize","2"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_return_status_isCreated_and_contentList_when_given_post_request() throws Exception {
        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content("{\"companiesID\":\"7\",\"companiesName\":\"huawei\"}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void should_return_status_isCreated_and_contentList_when_given_put_request() throws Exception {
        mockMvc.perform(put("/companies").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content("{\"companiesID\":\"7\",\"companiesName\":\"didi\"}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_status_isCreated_and_contentList_when_given_delete_request() throws Exception {
        mockMvc.perform(delete("/companies/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}