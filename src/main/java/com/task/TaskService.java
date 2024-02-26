package com.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
  @Autowired
  private TaskRepository taskRepository;

  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  @SuppressWarnings("null")
  public Task save(Task task) {
    return taskRepository.save(task);
  }

  public Task findById(long id) {
    return taskRepository.findById(id).orElse(null);
  }

  public void deleteById(long id) {
    taskRepository.deleteById(id);
  }

  public Task updateTask(long id, Task task) {
    task.setId(id);
    return taskRepository.save(task);
  }

  public Task patchTask(long id, Task task) {
    Task existingTask = taskRepository.findById(id).orElse(null);
    if (existingTask == null) {
      return null;
    }
    if (task.getTitle() != null) {
      existingTask.setTitle(task.getTitle());
    }
    if (task.getDescription() != null) {
      existingTask.setDescription(task.getDescription());
    }
    if (task.getDueDate() != null) {
      existingTask.setDueDate(task.getDueDate());
    }
    return taskRepository.save(existingTask);
  }
}
