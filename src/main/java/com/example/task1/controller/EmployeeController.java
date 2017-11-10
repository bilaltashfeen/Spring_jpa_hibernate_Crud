package com.example.task1.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.task1.model.Employee;
import com.example.task1.repository.EmloyeeRepositiory;
import com.example.task1.repository.NoteRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmloyeeRepositiory emprepo;

    
    @GetMapping("/hello2")
    public String gethome() {
    	return "hello2";
    }
    
    // Get All Notes
    @GetMapping("/emp")
    public List<Employee> getAllEmployee() {
        return emprepo.findAll();
    }
    // Create a new Note
    @PostMapping("/emp")
    public Employee createNote(@RequestBody Employee employee) {
        return emprepo.save(employee);
    }
    // Get a Single Note
    @GetMapping("/emp/{id}")
    public ResponseEntity<Employee> getNoteById(@PathVariable(value = "id") Long employeeId) {
    	Employee employee = emprepo.findOne(employeeId);
        if(employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employee);
    }
    // Update a Note
    
    @PutMapping("/emp/{id}")
    public ResponseEntity<Employee> updateNote(@PathVariable(value = "id") Long employeeId, 
                                           @Valid @RequestBody Employee employeeDetails) {
    	Employee employee = emprepo.findOne(employeeId);
        if(employee == null) {
            return ResponseEntity.notFound().build();
        }
        employee.setEmployeename(employeeDetails.getEmployeename());
        employee.setDesignation(employeeDetails.getDesignation());

        Employee updatedEmployee = emprepo.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    // Delete a Note	
    @DeleteMapping("/emp/{id}")
    public ResponseEntity<Employee> deleteNote(@PathVariable(value = "id") Long employeeId) {
    	Employee employee = emprepo.findOne(employeeId);
        if(employee == null) {
            return ResponseEntity.notFound().build();
        }

        emprepo.delete(employee);
        return ResponseEntity.ok().build();
    }
    
    
}