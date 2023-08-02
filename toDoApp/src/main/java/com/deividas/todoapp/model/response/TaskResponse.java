package com.deividas.todoapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskResponse {

    private Integer id;
    private String title;
    private Integer status;

}
