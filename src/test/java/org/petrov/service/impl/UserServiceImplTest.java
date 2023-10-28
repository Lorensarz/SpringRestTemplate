package org.petrov.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.petrov.dto.UserDto;
import org.petrov.dto.mapper.UserDtoMapper;
import org.petrov.entity.UserEntity;
import org.petrov.repository.UserRepository;
import org.petrov.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDtoMapper userDtoMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        long userId = 1L;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        UserDto userDto = new UserDto();
        when(userDtoMapper.toDto(userEntity)).thenReturn(userDto);

        UserDto result = userService.findById(userId);

        assertEquals(userDto, result);
    }

    @Test
    public void testDeleteById() {
        long userId = 1L;

        userService.deleteById(userId);

        verify(userRepository).deleteById(userId);
    }

    @Test
    public void testFindAll() {
        List<UserEntity> userList = new ArrayList<>();
        userList.add(new UserEntity());
        userList.add(new UserEntity());

        when(userRepository.findAll()).thenReturn(userList);

        List<UserEntity> result = userService.findAll();

        assertEquals(userList, result);
    }

    @Test
    public void testSave() {
        UserDto userDto = new UserDto();
        UserEntity userEntity = new UserEntity();

        when(userDtoMapper.toEntity(userDto)).thenReturn(userEntity);

        userService.save(userDto);


        verify(userRepository).save(userEntity);
    }

    @Test
    public void testUpdate() {
        UserDto userDto = new UserDto();
        UserEntity userEntity = new UserEntity();

        when(userDtoMapper.toEntity(userDto)).thenReturn(userEntity);

        userService.update(userDto);

        verify(userRepository).save(userEntity);
    }
}
