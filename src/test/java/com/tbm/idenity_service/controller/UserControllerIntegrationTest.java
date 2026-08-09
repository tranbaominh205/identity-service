package com.tbm.idenity_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tbm.idenity_service.dto.request.UserCreationRequest;
import com.tbm.idenity_service.dto.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
public class UserControllerIntegrationTest {

    @Container

    static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.36");



    @DynamicPropertySource
    static void configureDatasource(DynamicPropertyRegistry registry){

        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
        registry.add("spring.datasource.driver-class-name",() -> "com.mysql.cj.jdbc.Driver");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
    }

    @Autowired
    private MockMvc mockMvc;

    private UserCreationRequest userCreationRequest;
    private UserResponse userResponse;
    private LocalDate dob;

    @BeforeEach
    void initData(){
        dob = LocalDate.of(2000,8,20);
        userCreationRequest = UserCreationRequest.builder()
                .username("test01")
                .password("12345678")
                .firstname("Thu")
                .lastname("Kiem")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .username("test01")
                .firstname("Thu")
                .lastname("Kiem")
                .id("84a93d266907")
                .dob(dob)
                .build();

    }

    @Test
     void createUser_validRequest_succes() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()) ;
        String content = objectMapper.writeValueAsString(userCreationRequest);



         var response = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                 .contentType(MediaType.APPLICATION_JSON_VALUE)
                 .content(content))
                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("code")
                         .value("1100"))
                 .andExpect(MockMvcResultMatchers.jsonPath("result.username")
                         .value("test01"))
                 .andExpect(MockMvcResultMatchers.jsonPath("result.firstname")
                         .value("Thu"))
                 .andExpect(MockMvcResultMatchers.jsonPath("result.lastname")
                         .value("Kiem"))

         ;
        log.info("Result: {}", response.andReturn().getResponse().getContentAsString());

    }

}
