package org.petrov.service;

import org.petrov.dto.PostDto;
import org.petrov.dto.TagDto;
import org.petrov.dto.mapper.PostDtoMapper;
import org.petrov.dto.mapper.TagDtoMapper;
import org.petrov.entity.PostEntity;
import org.petrov.entity.TagEntity;
import org.petrov.repository.PostRepository;
import org.petrov.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final PostRepository postRepository;
    private final TagDtoMapper tagDtoMapper;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository,
                          PostRepository postRepository,
                          TagDtoMapper tagDtoMapper) {
        this.tagRepository = tagRepository;
        this.postRepository = postRepository;
        this.tagDtoMapper = tagDtoMapper;
    }

    @Override
    public void addTagToPost(long postId, TagDto tagDto) {
        String tagName = tagDto.getName();

        TagEntity existingTag = tagRepository.findByName(tagName);

        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        List<TagEntity> tags = postEntity.getTags();

        if (tags == null) {
            tags = new ArrayList<>();
            postEntity.setTags(tags);
        }
        if (existingTag == null) {
            TagEntity newTag = new TagEntity();
            newTag.setName(tagName);
            newTag = tagRepository.save(newTag);
            tags.add(newTag);
        } else {
            tags.add(existingTag);
        }
        postRepository.save(postEntity);
    }


    @Override
    public void removeTagFromPost(long postId, TagDto tagDto) {
        String tagName = tagDto.getName();

        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        TagEntity tagToRemove = tagRepository.findByName(tagName);

        if (tagToRemove != null) {
            postEntity.getTags().remove(tagToRemove);

            postRepository.save(postEntity);
        } else {
            throw new RuntimeException("Tag not found");
        }
    }

    @Override
    public List<TagDto> findTagsByPostId(long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElse(null);

        if (postEntity != null) {
            List<TagEntity> tags = postEntity.getTags();
            return tagDtoMapper.toDtoList(tags);
        } else {
            return Collections.emptyList();
        }
    }


}
