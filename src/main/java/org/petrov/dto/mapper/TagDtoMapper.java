package org.petrov.dto.mapper;

import org.petrov.entity.TagEntity;
import org.petrov.dto.TagDto;

import java.util.List;

public interface TagDtoMapper {
    TagDto toDto(TagEntity tag);

    TagEntity toEntity(TagDto dto);

    List<TagDto> toDtoList(List<TagEntity> tags);

    List<TagEntity> toEntityList(List<TagDto> dtoList);
}
