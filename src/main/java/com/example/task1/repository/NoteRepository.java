package com.example.task1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task1.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {


}