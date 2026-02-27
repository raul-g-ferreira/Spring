package com.kaizen.todo_list.service;

import com.kaizen.todo_list.model.Task;
import com.kaizen.todo_list.model.TaskStatus;
import com.kaizen.todo_list.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public void save(Task task) {
        taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public void setStatus(Long id, int statusCode) {
        Task task = (Task) taskRepository.findById(id).orElse(null);
        task.setStatus(TaskStatus.values()[statusCode]);
        taskRepository.save(task);
    }
}
