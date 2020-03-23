package com.example.springboot.demo.Mapper;

import com.example.springboot.demo.bean.Task;

import java.util.List;

public interface TaskMapper {
    public Task getById(int id);
    public List<Task> getTasks();
    public boolean updateTask(Task task);
    public boolean deleteTask(int id);
    public boolean deleteAllTasks();
}
