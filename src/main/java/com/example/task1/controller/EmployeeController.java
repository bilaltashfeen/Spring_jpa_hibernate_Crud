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
    public List<Employee> getAllNotes() {
        return emprepo.findAll();
    }
    // Create a new Note
    @PostMapping("/emp")
    public Employee createNote(@RequestBody Employee note) {
        return emprepo.save(note);
    }
    // Get a Single Note
    @GetMapping("/emp/{id}")
    public ResponseEntity<Employee> getNoteById(@PathVariable(value = "id") Long noteId) {
    	Employee note = emprepo.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(note);
    }
    // Update a Note
    
    @PutMapping("/emp/{id}")
    public ResponseEntity<Employee> updateNote(@PathVariable(value = "id") Long noteId, 
                                           @Valid @RequestBody Employee noteDetails) {
    	Employee note = emprepo.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }
        note.setEmployeename(noteDetails.getEmployeename());
        note.setDesignation(noteDetails.getDesignation());

        Employee updatedNote = emprepo.save(note);
        return ResponseEntity.ok(updatedNote);
    }
    // Delete a Note	
    @DeleteMapping("/emp/{id}")
    public ResponseEntity<Employee> deleteNote(@PathVariable(value = "id") Long noteId) {
    	Employee note = emprepo.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }

        emprepo.delete(note);
        return ResponseEntity.ok().build();
    }
    
}