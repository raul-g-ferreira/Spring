package com.kaizen.todo_list.controller;

import com.kaizen.todo_list.model.Task;
import com.kaizen.todo_list.model.TaskStatus;
import com.kaizen.todo_list.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/get")
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @PostMapping("/create")
    public void create(@RequestBody Task task) {
        taskService.save(task);
    }

    @PostMapping("/status/{id}/{statusCode}")
    public void setStatus(@PathVariable Long id, @PathVariable int statusCode) {
        taskService.setStatus(id, statusCode);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {taskService.delete(id);}

}
