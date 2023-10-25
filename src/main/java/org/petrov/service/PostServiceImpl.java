package org.petrov.service;

import org.petrov.entity.PostEntity;
import org.petrov.repository.PostRepository;
import org.petrov.service.PostService;
import org.petrov.dto.PostDto;
import org.petrov.dto.TagDto;
import org.petrov.dto.mapper.PostDtoMapper;
import org.petrov.dto.mapper.TagDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostDtoMapper postDtoMapper;
    private final TagDtoMapper tagDtoMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostDtoMapper postDtoMapper, TagDtoMapper tagDtoMapper) {
        this.postRepository = postRepository;
        this.postDtoMapper = postDtoMapper;
        this.tagDtoMapper = tagDtoMapper;
    }

    @Override
    public List<PostEntity> findPostsByUserId(long id) {
        return postRepository.findPostsByUserId(id);
    }

    @Override
    public List<PostEntity> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void save(PostDto postDto) {
        PostEntity postEntity = postDtoMapper.toEntity(postDto);
        postRepository.save(postEntity);
    }

    @Override
    public void update(PostDto postDto) {
        PostEntity postEntity = postDtoMapper.toEntity(postDto);
        postRepository.save(postEntity);
    }

    @Override
    public void deleteById(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostEntity> findPostsByTag(TagDto tagDto) {
        return postRepository.findPostsByTagEntity(tagDtoMapper.toEntity(tagDto));
    }
}