package org.petrov.dto.mapper;

import org.petrov.entity.UserEntity;
import org.petrov.dto.UserDto;

import java.util.Collection;
import java.util.List;

public interface UserDtoMapper {
    UserDto toDto(UserEntity user);

    UserEntity toEntity(UserDto dto);

    List<UserDto> toDtoList(Collection<UserEntity> users);

}
