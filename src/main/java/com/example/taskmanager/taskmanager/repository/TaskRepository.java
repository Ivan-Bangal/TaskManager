package com.example.taskmanager.taskmanager.repository;

import java.util.List;

import com.example.taskmanager.taskmanager.model.Task;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task,Long> {
    
    List<Task> findAll();

    Task findById(long id);

    void deleteById(long id);

    Task findByDescricao(String descricao);
  
}
