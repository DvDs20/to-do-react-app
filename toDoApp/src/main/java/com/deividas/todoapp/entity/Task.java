package com.deividas.todoapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task", schema = "to_do")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "to_do.task_id_seq")
    @SequenceGenerator(name = "to_do.task_id_seq",
                       sequenceName = "to_do.task_id_seq", allocationSize = 1)
    private Integer id;
    private String title;
    private Integer status;
}
