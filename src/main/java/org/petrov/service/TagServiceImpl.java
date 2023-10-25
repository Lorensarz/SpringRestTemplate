package org.petrov.service;

import org.petrov.entity.TagEntity;
import org.petrov.repository.TagRepository;
import org.petrov.service.TagService;
import org.petrov.dto.PostDto;
import org.petrov.dto.TagDto;
import org.petrov.dto.mapper.PostDtoMapper;
import org.petrov.dto.mapper.TagDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagDtoMapper tagDtoMapper;
    private final PostDtoMapper postDtoMapper;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagDtoMapper tagDtoMapper, PostDtoMapper postDtoMapper) {
        this.tagRepository = tagRepository;
        this.tagDtoMapper = tagDtoMapper;
        this.postDtoMapper = postDtoMapper;
    }

    @Override
    public void addTagToPost(PostDto post) {
        org.petrov.entity.PostEntity postEntity = postDtoMapper.toEntity(post);
        tagRepository.addTagToPost(postEntity);
    }

    @Override
    public void updateTagForPost(PostDto post) {
        org.petrov.entity.PostEntity postEntity = postDtoMapper.toEntity(post);
        tagRepository.updateTagsForPost(postEntity);
    }

    @Override
    public void removeTagFromPost(PostDto post) {
        org.petrov.entity.PostEntity postEntity = postDtoMapper.toEntity(post);
        tagRepository.removeTagFromPost(postEntity);
    }

    @Override
    public List<TagDto> findTagsByPost(PostDto postDto) {
        List<TagEntity> tags = tagRepository.findTagsByPostId(postDtoMapper.toEntity(postDto));
        return tagDtoMapper.toDtoList(tags);
    }


}
