package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Companies;
import com.tw.apistackbase.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static List<Employee> employeeList = new ArrayList<Employee>(){{
        add(new Employee(1,"xiaohuang","male",18,1));
        add(new Employee(2,"xiaofei","male",18,1));
        add(new Employee(3,"xiaona","female",18,3));
        add(new Employee(4,"xiaoyang","female",18,3));
    }};

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(@RequestParam(required = false) String gender){
        if (gender==null)
        return ResponseEntity.ok(employeeList);
        else {
            List<Employee> employees = new ArrayList<>();
            for (Employee employee:employeeList){
                if (employee.getGender().equals(gender)) employees.add(employee);
            }
            return ResponseEntity.ok(employees);
        }
    }
    @GetMapping("/{employeeID}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable int employeeID){
        for(Employee employee:employeeList){
            if (employee.getEmployeeID()==employeeID){
                return ResponseEntity.ok(employee);
            }
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/pages")
    public ResponseEntity<List<Employee>> getCompaiesByPage(@RequestParam int page, @RequestParam int pageSize){
        if(employeeList.size()<=(page-1)*pageSize) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else {
            List<Employee> employeePages = new ArrayList<>();
            for(int i=(page-1)*pageSize;i<employeeList.size()&&i<page*pageSize;i++){
                employeePages.add(employeeList.get(i));
            }
            return ResponseEntity.ok(employeePages);
        }

    }
    @PostMapping
    public ResponseEntity<Employee> addCompanies(@RequestBody Employee employee){
        employeeList.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
