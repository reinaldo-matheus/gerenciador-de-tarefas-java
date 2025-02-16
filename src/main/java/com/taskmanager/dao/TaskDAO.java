package com.taskmanager.dao;

import com.taskmanager.model.Task;
import java.util.List;

public interface TaskDAO {
    void save(Task task);
    void update(Task task);
    void delete(Task task);
    Task findById(Long id);
    List<Task> findAll();
    List<Task> findByTitle(String title);
    List<Task> findByResponsible(String responsible);
    List<Task> findByPriority(Task.Priority priority);
}