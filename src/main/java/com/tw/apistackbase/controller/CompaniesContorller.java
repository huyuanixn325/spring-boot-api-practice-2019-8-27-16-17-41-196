package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Companies;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesContorller {

    private static List<Companies> companiess = new ArrayList<Companies>(){{
        add(new Companies(1,"百度"));
        add(new Companies(2,"腾讯"));
        add(new Companies(3,"阿里"));
        add(new Companies(4,"头条"));
    }};



    @GetMapping
    public ResponseEntity<List<Companies>> getAllCompaies(){

        return ResponseEntity.ok(companiess);
    }

    @GetMapping("/{companiesID}")
    public ResponseEntity<Companies> getAllCompaies(@PathVariable int companiesID){
        for(Companies companies:companiess){
            if (companies.getCompaniesID()==companiesID){
                return ResponseEntity.ok(companies);
            }
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/pages")
    public ResponseEntity<List<Companies>> getCompaiesByPage(@RequestParam int page,@RequestParam int pageSize){
        if(companiess.size()<=(page-1)*pageSize) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else {
            List<Companies> pageCompanies = new ArrayList<>();
            for(int i=(page-1)*pageSize;i<companiess.size()&&i<page*pageSize;i++){
              pageCompanies.add(companiess.get(i));
            }
            return ResponseEntity.ok(pageCompanies);
        }

    }

    @PostMapping
    public ResponseEntity<Companies> addCompanies(@RequestBody Companies companies){
        companiess.add(companies);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
//        private  void initCompanies() {
//            companiess.add(new Companies(1,"百度"));
//            companiess.add(new Companies(2,"腾讯"));
//            companiess.add(new Companies(3,"阿里"));
//            companiess.add(new Companies(4,"头条"));
//        }

}
