package com.deividas.todoapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.deividas.todoapp.model.request.TaskRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * {@link TaskController}
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskControllerTests extends BaseTaskControllerTests {

    private static final String CREATE_NEW_TASK_URI = "/api/v1/tasks";
    private static final String FIND_TASK_URI = "/api/v1/tasks/{id}";
    private static final String DELETE_TASK_URI = "/api/v1/tasks/{id}";
    private static final String FIND_ALL_TASKS_URI = "/api/v1/tasks";
    private static final String SET_TASK_TO_DONE_URI = "/api/v1/tasks/{id}";

    /**
     * {@link TaskController#createNewTask(TaskRequest)}
     */
    @Order(1)
    @Test
    @SneakyThrows
    void Given_taskRequest_When_createNewTask_Then_createdResponseStatus() {
        // given
        var taskRequest = buildTaskRequest();

        // when
        var exchangeResult = mockMvc
                .perform(
                        post(CREATE_NEW_TASK_URI)
                                .content(taskRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                );

        // then
        exchangeResult
                .andExpect(
                        status().isCreated()
                );
    }

    /**
     * {@link TaskController#findTask(Integer)}
     */
    @Order(2)
    @Test
    @SneakyThrows
    void Given_taskId_When_findTask_Then_okResponseStatus() {
        // given
        int taskId = fetchTestTaskId();

        // when
        var exchangeResult = mockMvc
                .perform(
                        get(FIND_TASK_URI, taskId)
                );

        // then
        exchangeResult
                .andExpect(
                        status().isOk()
                );
    }

    /**
     * {@link TaskController#setTaskToDone(Integer)}
     */
    @Order(3)
    @Test
    @SneakyThrows
    void Given_taskId_When_setTaskToDone_Then_acceptedResponseStatus() {
        // given
        int taskId = fetchTestTaskId();

        // when
        var exchangeResult = mockMvc
                .perform(
                        put(SET_TASK_TO_DONE_URI, taskId)
                );

        // then
        exchangeResult
                .andExpect(
                        status().isAccepted()
                );
    }

    /**
     * {@link TaskController#findAllTasks()}
     */
    @Order(4)
    @Test
    @SneakyThrows
    void When_findAllTasks_Then_okResponseStatus() {
        // given

        // when
        var exchangeResult = mockMvc
                .perform(
                        get(FIND_ALL_TASKS_URI)
                );

        // then
        exchangeResult
                .andExpect(
                        status().isOk()
                );
    }

    /**
     * {@link TaskController#deleteTask(Integer)}
     */
    @Order(5)
    @Test
    @SneakyThrows
    void Given_taskId_When_deleteTask_Then_acceptedResponseStatus() {
        // given
        int taskId = fetchTestTaskId();

        // when
        var exchangeResult = mockMvc
                .perform(
                        delete(DELETE_TASK_URI, taskId)
                );

        // then
        exchangeResult
                .andExpect(
                        status().isAccepted()
                );
    }

    @SneakyThrows
    private String buildTaskRequest() {
        return OBJECT_MAPPER.writeValueAsString(
                TaskRequest.builder()
                           .title(TESTING_TASK_TITLE)
                           .build()
        );
    }

}
