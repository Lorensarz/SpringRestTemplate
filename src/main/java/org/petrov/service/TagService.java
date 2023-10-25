package org.petrov.service;

import org.petrov.dto.PostDto;
import org.petrov.dto.TagDto;

import java.util.List;

public interface TagService {
    void addTagToPost(PostDto post);
    void removeTagFromPost(PostDto post);
    List<TagDto> findTagsByPost(PostDto postDto);
    void updateTagForPost(PostDto postDto);
}
