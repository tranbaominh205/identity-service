package com.tbm.idenity_service.service;

import com.tbm.idenity_service.dto.request.AuthenticationRequest;
import com.tbm.idenity_service.dto.response.UserResponse;
import com.tbm.idenity_service.exception.AppException;
import com.tbm.idenity_service.exception.ErrorCode;
import com.tbm.idenity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;
    public boolean authenticate(AuthenticationRequest request){
        var user = userRepository.findbyUsername(request.getUsername())
                .orElseThrow(()->new  AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
