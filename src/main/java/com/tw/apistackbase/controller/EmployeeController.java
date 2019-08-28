package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static List<Employee> employeeList = new ArrayList<Employee>(){{
        add(new Employee(1,"xiaohuang","male",18,1));
//        add(new Employee(2,"xiaofei","male",18,1));
//        add(new Employee(3,"xiaona","female",18,3));
//        add(new Employee(4,"xiaoyang","female",18,3));
    }};

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return ResponseEntity.ok(employeeList);
    }
}
