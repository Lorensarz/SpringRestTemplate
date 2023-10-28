package org.petrov.dto.mapper;

import org.junit.jupiter.api.Test;
import org.petrov.dto.PostDto;
import org.petrov.dto.TagDto;
import org.petrov.dto.mapper.PostDtoMapper;
import org.petrov.dto.mapper.PostDtoMapperImpl;
import org.petrov.dto.mapper.TagDtoMapperImpl;
import org.petrov.entity.PostEntity;
import org.petrov.entity.TagEntity;
import org.petrov.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PostDtoMapperImplTest {

    @Test
    public void testToDto() {
        // Arrange
        PostDtoMapper mapper = new PostDtoMapperImpl(new TagDtoMapperImpl());
        PostEntity postEntity = new PostEntity();
        postEntity.setId(1L);
        postEntity.setTitle("Заголовок");
        postEntity.setContent("Содержание");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        postEntity.setUser(userEntity);
        List<TagEntity> tagEntities = new ArrayList<>();
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName("Java");
        tagEntities.add(tagEntity);
        postEntity.setTags(tagEntities);

        // Act
        PostDto postDto = mapper.toDto(postEntity);

        // Assert
        assertEquals(1L, postDto.getId());
        assertEquals("Заголовок", postDto.getTitle());
        assertEquals("Содержание", postDto.getContent());
        assertEquals(1L, postDto.getUserId());
        assertEquals(1, postDto.getTags().size());
        assertEquals("Java", postDto.getTags().get(0).getName());
    }

    @Test
    public void testToEntity() {
        // Arrange
        PostDtoMapper mapper = new PostDtoMapperImpl(new TagDtoMapperImpl());
        PostDto postDto = new PostDto();
        postDto.setId(1L);
        postDto.setTitle("Заголовок");
        postDto.setContent("Содержание");
        postDto.setUserId(1L);
        List<TagDto> tagDtos = new ArrayList<>();
        TagDto tagDto = new TagDto();
        tagDto.setName("Java");
        tagDtos.add(tagDto);
        postDto.setTags(tagDtos);

        // Act
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        PostEntity postEntity = mapper.toEntity(postDto);
        postEntity.setUser(userEntity);

        // Assert
        assertEquals(1L, postEntity.getId());
        assertEquals("Заголовок", postEntity.getTitle());
        assertEquals("Содержание", postEntity.getContent());
        assertEquals(1L, postEntity.getUser().getId());
        assertEquals(1, postEntity.getTags().size());
        assertEquals("Java", postEntity.getTags().get(0).getName());
    }

    @Test
    public void testToDtoList() {
        // Arrange
        PostDtoMapper mapper = new PostDtoMapperImpl(new TagDtoMapperImpl());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);

        List<TagEntity> tagEntities = new ArrayList<>();
        TagEntity tagEntity = new TagEntity();
        tagEntity.setId(1L);
        tagEntity.setName("Tag");

        // Создаем несколько объектов PostEntity для теста
        PostEntity postEntity1 = new PostEntity();
        postEntity1.setId(1L);
        postEntity1.setTitle("Заголовок 1");
        postEntity1.setContent("Содержание 1");
        postEntity1.setUser(userEntity);
        postEntity1.setTags(tagEntities);

        PostEntity postEntity2 = new PostEntity();
        postEntity2.setId(2L);
        postEntity2.setTitle("Заголовок 2");
        postEntity2.setContent("Содержание 2");
        postEntity2.setUser(userEntity);
        postEntity2.setTags(tagEntities);

        List<PostEntity> postEntities = new ArrayList<>();
        postEntities.add(postEntity1);
        postEntities.add(postEntity2);

        // Act
        List<PostDto> postDtos = mapper.toDtoList(postEntities);

        // Assert
        assertEquals(2, postDtos.size());

        PostDto dto1 = postDtos.get(0);
        assertEquals(1L, dto1.getId());
        assertEquals("Заголовок 1", dto1.getTitle());
        assertEquals("Содержание 1", dto1.getContent());

        PostDto dto2 = postDtos.get(1);
        assertEquals(2L, dto2.getId());
        assertEquals("Заголовок 2", dto2.getTitle());
        assertEquals("Содержание 2", dto2.getContent());
    }

    @Test
    public void testToEntityList() {
        // Arrange
        PostDtoMapper mapper = new PostDtoMapperImpl(new TagDtoMapperImpl());

        // Создаем несколько объектов PostDto для теста
        PostDto postDto1 = new PostDto();
        postDto1.setId(1L);
        postDto1.setTitle("Заголовок 1");
        postDto1.setContent("Содержание 1");
        postDto1.setUserId(1L);
        postDto1.setTags(new ArrayList<>());

        PostDto postDto2 = new PostDto();
        postDto2.setId(2L);
        postDto2.setTitle("Заголовок 2");
        postDto2.setContent("Содержание 2");
        postDto2.setUserId(1L);
        postDto2.setTags(new ArrayList<>());

        List<PostDto> postsDto = new ArrayList<>();
        postsDto.add(postDto1);
        postsDto.add(postDto2);

        // Act
        List<PostEntity> postEntities = mapper.toEntityList(postsDto);

        // Assert
        assertEquals(2, postEntities.size());

        PostEntity entity1 = postEntities.get(0);
        assertEquals(1L, entity1.getId());
        assertEquals("Заголовок 1", entity1.getTitle());
        assertEquals("Содержание 1", entity1.getContent());
        assertEquals(1L, entity1.getUser().getId());

        PostEntity entity2 = postEntities.get(1);
        assertEquals(2L, entity2.getId());
        assertEquals("Заголовок 2", entity2.getTitle());
        assertEquals("Содержание 2", entity2.getContent());
        assertEquals(1L, entity2.getUser().getId());
    }


}
