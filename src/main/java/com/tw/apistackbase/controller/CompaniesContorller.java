package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Companies;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesContorller {

    private static List<Companies> companies = new ArrayList<Companies>(){{
        add(new Companies(1,"百度"));
        add(new Companies(2,"腾讯"));
    }};



    @GetMapping
    public ResponseEntity<List<Companies>> getAllCompaies(){
        initCompanies();
        return ResponseEntity.ok(companies);
    }


        private  void initCompanies() {
            companies.clear();
            companies.add(new Companies(1,"百度"));
            companies.add(new Companies(2,"腾讯"));
        }

}
