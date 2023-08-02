package com.deividas.todoapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
public abstract class BaseTest {

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setupTestsEnvironmentAndSaveAuthDataIntoField() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

}
