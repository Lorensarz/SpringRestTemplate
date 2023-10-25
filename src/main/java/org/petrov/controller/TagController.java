package org.petrov.controller;


import org.petrov.service.TagService;
import org.petrov.dto.PostDto;
import org.petrov.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<TagDto> getTagsByPost(@RequestBody PostDto postDto) {
        return tagService.findTagsByPost(postDto);
    }

    @PostMapping
    public void addTagToPost(@RequestBody PostDto postDto) {
        tagService.addTagToPost(postDto);
    }

    @PutMapping
    public void updateTagForPost(@RequestBody PostDto postDto) {
        tagService.updateTagForPost(postDto);
    }

    @DeleteMapping
    public void removeTagFromPost(@RequestBody PostDto postDto) {
        tagService.removeTagFromPost(postDto);
    }

}
