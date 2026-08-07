package com.tbm.idenity_service.service;


import com.tbm.idenity_service.dto.request.UserCreationRequest;
import com.tbm.idenity_service.dto.response.UserResponse;
import com.tbm.idenity_service.entity.User;
import com.tbm.idenity_service.exception.AppException;
import com.tbm.idenity_service.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    private UserCreationRequest userCreationRequest;
    private UserResponse userResponse;
    private User user;
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

        user = User.builder()
                .username("test01")
                .firstname("Thu")
                .lastname("Kiem")
                .id("84a93d266907")
                .dob(dob)
                .build();

    }

    @Test
    void createUser_validRequest_success(){
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        var response =  userService.createUser(userCreationRequest);

        Assertions.assertThat(response.getId()).isEqualTo("84a93d266907");
        Assertions.assertThat(response.getUsername()).isEqualTo("test01");

    }


    @Test
    void createUser_userExisted_fail(){
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        var exception = assertThrows(AppException.class,
                ()-> userService.createUser(userCreationRequest));

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1001);

    }
}
