package com.kaizen.todo_list.repository;

import com.kaizen.todo_list.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
