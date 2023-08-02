package com.deividas.todoapp.controller;

import com.deividas.todoapp.model.request.TaskRequest;
import com.deividas.todoapp.model.response.TaskResponse;
import com.deividas.todoapp.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/tasks")
@Tag(name = "To Do")
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Create new task")
    @ApiResponse(responseCode = "201", description = "New task created successfully")
    @PostMapping
    public ResponseEntity<TaskResponse> createNewTask(@RequestBody TaskRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.createNewTask(request));
    }

    @Operation(summary = "Find task by id")
    @ApiResponse(responseCode = "200", description = "Task founded successfully")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findTask(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.findTaskById(id));
    }

    @Operation(summary = "Delete task by id")
    @ApiResponse(responseCode = "202", description = "Task deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Find all tasks")
    @ApiResponse(responseCode = "200", description = "All tasks founded successfully")
    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAllTasks() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.findAllTasks());
    }

    @Operation(summary = "Set task to done status")
    @ApiResponse(responseCode = "202", description = "Task status changed successfully")
    @PutMapping("/{id}")
    public ResponseEntity<Void> setTaskToDone(@PathVariable Integer id) {
        taskService.setTaskToDone(id);
        return ResponseEntity.accepted().build();
    }

}
