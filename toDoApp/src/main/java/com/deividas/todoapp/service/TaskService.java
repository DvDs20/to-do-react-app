package com.deividas.todoapp.service;

import com.deividas.todoapp.entity.Task;
import com.deividas.todoapp.mapper.TaskMapper;
import com.deividas.todoapp.model.request.TaskRequest;
import com.deividas.todoapp.model.response.TaskResponse;
import com.deividas.todoapp.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private final static int DEFAULT_STATUS_VALUE = 1;
    private static final int STATUS_VALUE_DONE = 0;

    public TaskResponse createNewTask(TaskRequest request) {
        return taskMapper.toTaskResponse(
                taskRepository.save(
                        Task.builder()
                            .title(request.getTitle())
                            .status(DEFAULT_STATUS_VALUE)
                            .build()
                )
        );
    }

    public TaskResponse findTaskById(Integer id) {
        return taskMapper.toTaskResponse(
                taskRepository.findById(id)
                              .orElseThrow(() -> new RuntimeException("Task not found by id: " + id))
        );
    }

    public void deleteTask(Integer id) {
        var taskEntity = taskRepository.findById(id)
                                       .orElseThrow(() -> new RuntimeException("Task not found by id: " + id));

        taskRepository.delete(taskEntity);
    }

    public List<TaskResponse> findAllTasks() {
        return taskRepository.findAll()
                             .stream()
                             .map(taskMapper::toTaskResponse)
                             .toList();
    }

    public void setTaskToDone(Integer id) {
        var taskEntity = taskRepository.findById(id)
                                       .orElseThrow(() -> new RuntimeException("Task not found by id: " + id));

        taskRepository.save(Task.builder()
                                .id(taskEntity.getId())
                                .title(taskEntity.getTitle())
                                .status(STATUS_VALUE_DONE)
                                .build());
    }

}
