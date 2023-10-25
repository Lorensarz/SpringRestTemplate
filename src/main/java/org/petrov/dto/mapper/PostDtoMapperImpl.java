package org.petrov.dto.mapper;

import org.petrov.entity.PostEntity;
import org.petrov.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostDtoMapperImpl implements PostDtoMapper {

    private final TagDtoMapper tagMapper;

    @Autowired
    public PostDtoMapperImpl(TagDtoMapper tagMapper) {
        this.tagMapper = tagMapper;
    }


    @Override
    public PostDto toDto(PostEntity post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setUserId(post.getId());
        dto.setTags(tagMapper.toDtoList(post.getTags()));
        return dto;
    }

    @Override
    public PostEntity toEntity(PostDto dto) {
        PostEntity post = new PostEntity();
        post.setId(dto.getId());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setId(dto.getUserId());
        post.setTags(tagMapper.toEntityList(dto.getTags()));
        return post;
    }

    @Override
    public List<PostDto> toDtoList(List<PostEntity> posts) {
        List<PostDto> dtoList = new ArrayList<>();
        for (PostEntity post : posts) {
            dtoList.add(toDto(post));
        }
        return dtoList;
    }

    @Override
    public List<PostEntity> toEntityList(List<PostDto> dtoList) {
        List<PostEntity> posts = new ArrayList<>();
        for (PostDto dto : dtoList) {
            posts.add(toEntity(dto));
        }
        return posts;
    }
}
