package com.accion.consultation.service.impl;

import com.accion.consultation.entities.UserEntity;
import com.accion.consultation.mappers.UserMapper;
import com.accion.consultation.models.dto.user.UserDTO;
import com.accion.consultation.repositories.UserRepository;
import com.accion.consultation.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getUsers(Pageable pageable) {
        Page<UserEntity> foundUsers = userRepository.findAll(pageable);
        return foundUsers.stream().map(userMapper::toModel).toList();
    }
}
