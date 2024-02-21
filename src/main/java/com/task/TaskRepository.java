package com.task;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

// @Repository
// public class TaskRepository {
//   private final Map<Long, Task> tasks = new HashMap<>();

//   public Optional<Task> findById(long id) {
//     return Optional.ofNullable(tasks.get(id));
//   }

//   public void add(Task task) {
//     tasks.put(task.getId(), task);
//   }

//   public Collection<Task> getTasks() {
//     return tasks.values();
//   }

//   @SuppressWarnings("null")
//   public Page<Task> getTasks(Pageable pageable) {
//     int toSkip = pageable.getPageSize() * pageable.getPageNumber();
//     List<Task> result = tasks.values().stream().skip(toSkip).limit(pageable.getPageSize()).toList();

//     return new PageImpl<>(result, pageable, tasks.size());
//   }
// }
