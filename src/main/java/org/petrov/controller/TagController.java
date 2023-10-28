package org.petrov.controller;


import org.petrov.service.TagService;
import org.petrov.dto.PostDto;
import org.petrov.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/{postId}")
    public List<TagDto> getTagsByPost(@PathVariable long postId) {
        return tagService.findTagsByPostId(postId);
    }

    @PostMapping("/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTagToPost(@PathVariable long postId, @RequestBody TagDto tagDto) {
        tagService.addTagToPost(postId, tagDto);
    }

    @DeleteMapping("/{postId}")
    public void removeTagFromPost(@PathVariable long postId, @RequestBody TagDto tagDto) {
        tagService.removeTagFromPost(postId, tagDto);
    }

}
