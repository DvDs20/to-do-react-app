package com.deividas.todoapp.integrationTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import com.deividas.todoapp.model.request.TaskRequest;
import com.deividas.todoapp.model.response.TaskResponse;
import com.deividas.todoapp.repository.TaskRepository;
import com.deividas.todoapp.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskServiceIT extends BaseIT {

    private static final String INTEGRATION_TEST_TASK_TITLE = "TestTaskTitleIT";

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testCreateTask() {
        var taskRequest = new TaskRequest(INTEGRATION_TEST_TASK_TITLE);
        var createdTask = taskService.createNewTask(taskRequest);

        TaskResponse foundTask = taskService.findTaskById(createdTask.getId());

        assertThat(foundTask).isNotNull();
        assertThat(foundTask.getId()).isEqualTo(createdTask.getId());
        assertThat(foundTask.getTitle()).isEqualTo(createdTask.getTitle());
        assertThat(foundTask.getStatus()).isEqualTo(1);

        // Cleanup
        taskService.deleteTask(foundTask.getId());

        // Ensure task is deleted
        assertThrows(RuntimeException.class, () -> taskService.findTaskById(foundTask.getId()));
    }

    @Test
    public void testFindTaskById() {
        var taskRequest = new TaskRequest(INTEGRATION_TEST_TASK_TITLE);
        var createdTask = taskService.createNewTask(taskRequest);
        var createdTaskId = createdTask.getId();

        TaskResponse foundTask = taskService.findTaskById(createdTaskId);

        assertThat(foundTask).isNotNull();
        assertThat(foundTask.getId()).isEqualTo(createdTask.getId());
        assertThat(foundTask.getTitle()).isEqualTo(createdTask.getTitle());
        assertThat(foundTask.getStatus()).isEqualTo(createdTask.getStatus());

        // Cleanup
        taskService.deleteTask(createdTask.getId());

        // Ensure task is deleted
        assertThrows(RuntimeException.class, () -> taskService.findTaskById(createdTask.getId()));
    }

    @Test
    public void testDeleteTask() {
        var taskRequest = new TaskRequest(INTEGRATION_TEST_TASK_TITLE);
        var createdTaskId = taskService.createNewTask(taskRequest).getId();

        taskService.deleteTask(createdTaskId);

        assertThat(taskRepository.findById(createdTaskId)).isEmpty();
    }

    @Test
    public void testAllTasks() {
        var tasks = taskService.findAllTasks();

        assertThat(tasks).isNotNull();
    }

    @Test
    public void testSetTaskToDone() {
        var taskRequest = new TaskRequest(INTEGRATION_TEST_TASK_TITLE);
        var createdTask = taskService.createNewTask(taskRequest);
        var createdTaskId = createdTask.getId();

        taskService.setTaskToDone(createdTaskId);

        assertThat(taskService.findTaskById(createdTaskId).getStatus()).isEqualTo(0);
    }

}
