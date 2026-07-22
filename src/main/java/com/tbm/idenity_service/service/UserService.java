package com.tbm.idenity_service.service;

import com.tbm.idenity_service.dto.request.UserUpdateRequest;
import com.tbm.idenity_service.entity.User;
import com.tbm.idenity_service.exception.AppException;
import com.tbm.idenity_service.exception.ErrorCode;
import com.tbm.idenity_service.repository.UserRepository;
import com.tbm.idenity_service.dto.request.UserCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User createUser(UserCreationRequest request){
        User user = new User();

        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public List<User> getUser()
    {
        return userRepository.findAll();
    }

    public User getUser(String userId)
    {
        return userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
    }

    public User updateUser(String userId, UserUpdateRequest request){
        User user = getUser(userId);
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setDob(request.getDob());

        return userRepository.save(user);

    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
