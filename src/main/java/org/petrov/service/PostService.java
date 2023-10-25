package org.petrov.service;

import org.petrov.entity.PostEntity;
import org.petrov.dto.PostDto;
import org.petrov.dto.TagDto;

import java.util.List;

public interface PostService {

    List<PostEntity> findPostsByUserId(long id);

    List<PostEntity> findAll();
    void save(PostDto postDto);
    void update(PostDto postDto);
    void deleteById(long id);
    List<PostEntity> findPostsByTag(TagDto tagDto);
}
