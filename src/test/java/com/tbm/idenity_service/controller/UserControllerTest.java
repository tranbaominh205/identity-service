package com.tbm.idenity_service.controller;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tbm.idenity_service.dto.request.UserCreationRequest;
import com.tbm.idenity_service.dto.response.UserResponse;
import com.tbm.idenity_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

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
        Mockito.when(userService.createUser(ArgumentMatchers.any()))
                .thenReturn(userResponse);


        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value("1100"))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id")
                        .value("84a93d266907"))

        ;

    }

    @Test
    void createUser_UsernameInvalid_fail() throws Exception {

        userCreationRequest.setUsername("t01");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()) ;
        String content = objectMapper.writeValueAsString(userCreationRequest);



        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value("1002"))
                .andExpect(MockMvcResultMatchers.jsonPath("message")
                        .value("Username must be at least 4 characters"))

        ;

    }

}
