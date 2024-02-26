package com.task;

import java.util.Collection;
import java.util.Objects;
// import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springdoc.core.annotations.ParameterObject;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
// import org.springdoc.core.annotations.ParameterObject;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {
  private final TaskRepository repository;

  public TaskController(TaskRepository repository) {
    this.repository = repository;
  }

  @Operation(summary = "Get a task by its id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the task", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
      @ApiResponse(responseCode = "404", description = "Task not found", content = @Content) }) // @formatter:on
  @GetMapping("/{id}")
  public Task findById(@Parameter(description = "id of task to be searched") @PathVariable long id) {
    return repository.findById(id)
        .orElseThrow(TaskNotFoundException::new);
  }

  @GetMapping("/")
  public Collection<Task> findTasks() {
    return repository.findAll();
  }

  // @SuppressWarnings("null")
  // @GetMapping("/filter")
  // public Page<Task> filterTasks(@ParameterObject Pageable pageable) {
  //   return repository.findAll(pageable);
  // }

  @SuppressWarnings("null")
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Task updateTask(@PathVariable("id") final long id, @RequestBody final Task task) {
    task.setId(id);
    return repository.save(task);
  }
  
  @SuppressWarnings("null")
  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Task patchTask(@PathVariable("id") final long id, @RequestBody final Task task) {
    Task taskDB = repository.findById(id).orElseThrow(TaskNotFoundException::new);

    if (Objects.nonNull(task.getTitle()) && !"".equalsIgnoreCase(task.getTitle())) {
      taskDB.setTitle(task.getTitle());
    }
    if (Objects.nonNull(task.getDescription()) && !"".equalsIgnoreCase(task.getDescription())) {
      taskDB.setDescription(task.getDescription());
    }
    if (Objects.nonNull(task.getDueDate())) {
      taskDB.setDueDate(task.getDueDate());
    }
    return repository.save(taskDB);
  }

  @SuppressWarnings("null")
  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public Task postTask(@NotNull @Valid @RequestBody final Task task) {
    return repository.save(task);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteTask(@PathVariable final long id) {
    repository.deleteById(id);
  }
}