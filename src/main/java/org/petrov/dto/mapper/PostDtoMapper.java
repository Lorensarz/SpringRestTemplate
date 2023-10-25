package org.petrov.dto.mapper;

import org.petrov.entity.PostEntity;
import org.petrov.dto.PostDto;

import java.util.List;

public interface PostDtoMapper {
    PostDto toDto(PostEntity post);

    PostEntity toEntity(PostDto dto);

    List<PostDto> toDtoList(List<PostEntity> posts);

    List<PostEntity> toEntityList(List<PostDto> dtoList);
}
