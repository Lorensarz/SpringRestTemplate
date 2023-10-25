package org.petrov.service;

import org.petrov.dto.UserDto;
import org.petrov.dto.mapper.UserDtoMapper;
import org.petrov.entity.UserEntity;
import org.petrov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserDtoMapper userDtoMapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserDtoMapper userDtoMapper) {
        this.repository = repository;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public UserDto findById(long id) {
        return userDtoMapper.toDto(repository.findById(id).orElse(null));

    }

    @Override
    public void deleteById(long userId) {
        repository.deleteById(userId);
    }

    @Override
    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(UserDto userDto) {
        repository.save(userDtoMapper.toEntity(userDto));
    }

    @Override
    public void update(UserDto userDto) {
        repository.save(userDtoMapper.toEntity(userDto));
    }
}
