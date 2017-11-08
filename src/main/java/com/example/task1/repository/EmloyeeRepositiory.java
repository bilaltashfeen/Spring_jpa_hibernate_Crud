package com.example.task1.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task1.model.Employee;

@Repository
public interface EmloyeeRepositiory extends JpaRepository<Employee, Long> {
//	void save(); 
//	void findOne();
//	List<Note> findAll();
//	long count();
//	void delete();

}