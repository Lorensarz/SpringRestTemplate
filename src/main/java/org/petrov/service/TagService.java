package org.petrov.service;

import org.petrov.dto.PostDto;
import org.petrov.dto.TagDto;

import java.util.List;

public interface TagService {
    void addTagToPost(long postId, TagDto tagDto);
    void removeTagFromPost(long postId, TagDto tagDto);
    List<TagDto> findTagsByPostId(long postId);
}
