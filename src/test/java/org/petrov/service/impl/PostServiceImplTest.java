package org.petrov.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.petrov.dto.PostDto;
import org.petrov.dto.TagDto;
import org.petrov.dto.mapper.PostDtoMapper;
import org.petrov.dto.mapper.TagDtoMapper;
import org.petrov.entity.PostEntity;
import org.petrov.entity.TagEntity;
import org.petrov.repository.PostRepository;
import org.petrov.service.PostServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostDtoMapper postDtoMapper;

    @Mock
    private TagDtoMapper tagDtoMapper;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindPostsByUserId() {
        // Arrange
        long userId = 1L;
        List<PostEntity> postList = new ArrayList<>();
        when(postRepository.findPostsByUserId(userId)).thenReturn(postList);

        // Act
        List<PostEntity> result = postService.findPostsByUserId(userId);

        // Assert
        assertEquals(postList, result);
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<PostEntity> postList = new ArrayList<>();
        when(postRepository.findAll()).thenReturn(postList);

        // Act
        List<PostEntity> result = postService.findAll();

        // Assert
        assertEquals(postList, result);
    }

    @Test
    public void testSave() {
        // Arrange
        PostDto postDto = new PostDto();
        PostEntity postEntity = new PostEntity();
        when(postDtoMapper.toEntity(postDto)).thenReturn(postEntity);

        // Act
        postService.save(postDto);

        // Assert
        verify(postRepository).save(postEntity);
    }

    @Test
    public void testUpdate() {
        // Arrange
        PostDto postDto = new PostDto();
        PostEntity postEntity = new PostEntity();
        when(postDtoMapper.toEntity(postDto)).thenReturn(postEntity);

        // Act
        postService.update(postDto);

        // Assert
        verify(postRepository).save(postEntity);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        long postId = 1L;

        // Act
        postService.deleteById(postId);

        // Assert
        verify(postRepository).deleteById(postId);
    }

    @Test
    public void testFindPostsByTag() {
        // Arrange
        TagDto tagDto = new TagDto();
        TagEntity tagEntity = new TagEntity();
        PostEntity postEntity = new PostEntity();
        List<PostEntity> postList = new ArrayList<>();
        when(tagDtoMapper.toEntity(tagDto)).thenReturn(tagEntity);
        when(postRepository.findPostsByTagEntity(tagEntity)).thenReturn(postList);

        // Act
        List<PostEntity> result = postService.findPostsByTag(tagDto);

        // Assert
        assertEquals(postList, result);
    }
}
