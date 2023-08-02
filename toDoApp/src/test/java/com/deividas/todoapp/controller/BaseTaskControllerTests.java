package com.deividas.todoapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.deividas.todoapp.BaseTest;
import com.deividas.todoapp.model.response.TaskResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;

public abstract class BaseTaskControllerTests extends BaseTest {

    private static final String GET_ALL_TASKS_URI = "/api/v1/tasks";
    protected static final String TESTING_TASK_TITLE = "TestTaskName";

    @SneakyThrows
    public Integer fetchTestTaskId() {
        List<TaskResponse> tasks = OBJECT_MAPPER.readValue(mockMvc
                        .perform(
                                get(GET_ALL_TASKS_URI)
                        )
                        .andReturn()
                        .getResponse()
                        .getContentAsString(), new TypeReference<>() {
                }
        );

        Optional<TaskResponse> taskResponseOptional = tasks
                .stream()
                .filter(taskResponse -> taskResponse.getTitle().equals(TESTING_TASK_TITLE))
                .findFirst();

        return taskResponseOptional.map(TaskResponse::getId).orElse(null);
    }

}
