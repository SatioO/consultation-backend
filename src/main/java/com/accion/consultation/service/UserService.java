package com.accion.consultation.service;

import com.accion.consultation.models.dto.user.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {
    List<UserDTO> getUsers(Pageable pageable);
}
