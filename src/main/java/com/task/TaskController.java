package com.task;

import java.util.Collection;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    // return repository.getTasks();
  }

  @GetMapping("/filter")
  public Page<Task> filterTasks(@ParameterObject Pageable pageable) {
    return repository.findAll(pageable);
    // return repository.getTasks(pageable);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Task updateTask(@PathVariable("id") final String id, @RequestBody final Task task) {
    return task;
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Task patchTask(@PathVariable("id") final String id, @RequestBody final Task task) {
    return task;
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public Task postTask(@NotNull @Valid @RequestBody final Task task) {
    return task;
  }

  @RequestMapping(method = RequestMethod.HEAD, value = "/")
  @ResponseStatus(HttpStatus.OK)
  public Task headTask() {
    return new Task();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public long deleteTask(@PathVariable final long id) {
    return id;
  }
}