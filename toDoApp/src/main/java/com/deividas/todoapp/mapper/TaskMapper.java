package com.deividas.todoapp.mapper;

import com.deividas.todoapp.entity.Task;
import com.deividas.todoapp.model.response.TaskResponse;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponse toTaskResponse(Task task);
}
