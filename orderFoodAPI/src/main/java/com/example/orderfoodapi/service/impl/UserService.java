package com.example.orderfoodapi.service.impl;

import com.example.orderfoodapi.converter.UserConverter;
import com.example.orderfoodapi.dto.UserDTO;
import com.example.orderfoodapi.entity.User;
import com.example.orderfoodapi.repository.UserReponsitory;
import com.example.orderfoodapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserReponsitory userReponsitory;
    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userConverter.toEntity(userDTO);
        user = userReponsitory.save(user);
        return userConverter.toDTO(user);
    }
}