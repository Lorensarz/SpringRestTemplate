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
import org.petrov.repository.TagRepository;
import org.petrov.service.TagServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TagServiceImplTest {

    @Mock
    private TagRepository tagRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private TagDtoMapper tagDtoMapper;

    @Mock
    private PostDtoMapper postDtoMapper;

    @InjectMocks
    private TagServiceImpl tagService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTagToPost_ExistingTag() {
        // Arrange
        long postId = 1L;
        TagDto tagDto = new TagDto();
        tagDto.setName("Tag 1");

        PostEntity postEntity = new PostEntity();
        TagEntity existingTag = new TagEntity();
        existingTag.setName("Tag 1");


        when(tagRepository.findByName("Tag 1")).thenReturn(existingTag);
        when(postRepository.findById(postId)).thenReturn(Optional.of(postEntity));

        // Act
        tagService.addTagToPost(postId, tagDto);

        // Assert
        assertEquals(1, postEntity.getTags().size());
        assertEquals(existingTag, postEntity.getTags().get(0));
    }

    @Test
    public void testAddTagToPost_NewTag() {
        // Arrange
        long postId = 1L;
        TagDto tagDto = new TagDto();
        tagDto.setName("Java");

        PostEntity postEntity = new PostEntity();
        TagEntity newTag = new TagEntity();
        newTag.setName("Java");

        when(tagRepository.findByName("Java")).thenReturn(null);
        when(tagRepository.save(any(TagEntity.class))).thenReturn(newTag);
        when(postRepository.findById(postId)).thenReturn(Optional.of(postEntity));

        // Act
        tagService.addTagToPost(postId, tagDto);

        // Assert
        assertEquals(1, postEntity.getTags().size());
        assertEquals(newTag, postEntity.getTags().get(0));
    }

    @Test
    public void testRemoveTagFromPost_TagExists() {
        // Arrange
        long postId = 1L;
        TagDto tagDto = new TagDto();
        tagDto.setName("Java");

        PostEntity postEntity = new PostEntity();
        postEntity.setTags(new ArrayList<>());
        TagEntity tagToRemove = new TagEntity();
        tagToRemove.setName("Java");
        postEntity.getTags().add(tagToRemove);

        when(postRepository.findById(postId)).thenReturn(Optional.of(postEntity));
        when(tagRepository.findByName("Java")).thenReturn(tagToRemove);

        // Act
        tagService.removeTagFromPost(postId, tagDto);

        // Assert
        assertEquals(0, postEntity.getTags().size());
    }

    @Test
    public void testRemoveTagFromPost_TagNotExists() {
        // Arrange
        long postId = 1L;
        TagDto tagDto = new TagDto();
        tagDto.setName("Java");

        PostEntity postEntity = new PostEntity();
        when(postRepository.findById(postId)).thenReturn(Optional.of(postEntity));
        when(tagRepository.findByName("Java")).thenReturn(null);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> tagService.removeTagFromPost(postId, tagDto));
    }

    @Test
    public void testFindTagsByPostId_PostExists() {
        // Arrange
        long postId = 1L;
        PostEntity postEntity = new PostEntity();
        PostDto postDto = new PostDto();
        postEntity.setId(postId);
        postDto.setId(postId);

        List<TagEntity> tagEntities = new ArrayList<>();
        List<TagDto> tagDtos = new ArrayList<>();

        TagEntity tag1 = new TagEntity();
        tag1.setName("Java");
        tagEntities.add(tag1);

        TagDto tagDto1 = new TagDto();
        tagDto1.setName("Java");
        tagDtos.add(tagDto1);

        TagEntity tag2 = new TagEntity();
        tag2.setName("Spring");
        tagEntities.add(tag2);

        TagDto tagDto2 = new TagDto();
        tagDto2.setName("Spring");
        tagDtos.add(tagDto2);

        postEntity.setTags(tagEntities);
        postDto.setTags(tagDtos);

        when(postRepository.findById(postId)).thenReturn(Optional.of(postEntity));
        when(tagDtoMapper.toDtoList(tagEntities)).thenReturn(tagDtos);
        // Act
        List<TagDto> result = tagService.findTagsByPostId(postId);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Java", result.get(0).getName());
        assertEquals("Spring", result.get(1).getName());
    }

    @Test
    public void testFindTagsByPostId_PostNotExists() {
        // Arrange
        long postId = 1L;
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // Act
        List<TagDto> result = tagService.findTagsByPostId(postId);

        // Assert
        assertEquals(0, result.size());
    }
}
