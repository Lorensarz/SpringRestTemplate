package org.petrov.service;

import org.petrov.entity.UserEntity;
import org.petrov.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto findById(long id);

    void deleteById(long id);

    List<UserEntity> findAll();

    void save(UserDto user);

    void update(UserDto user);


}
