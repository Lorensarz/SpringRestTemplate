package org.petrov.controller;

import org.petrov.service.PostService;
import org.petrov.dto.PostDto;
import org.petrov.dto.mapper.PostDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final PostDtoMapper postDtoMapper;

    @Autowired
    public PostController(PostService postService, PostDtoMapper postDtoMapper) {
        this.postService = postService;
        this.postDtoMapper = postDtoMapper;
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postDtoMapper.toDtoList(postService.findAll());
    }

    @GetMapping("/{userId}")
    public List<PostDto> getPostsByUserId(@PathVariable Long userId) {
        return postDtoMapper.toDtoList(postService.findPostsByUserId(userId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostDto postDto) {
        postService.save(postDto);
    }

    @PutMapping
    public void updatePost(@RequestBody PostDto postDto) {
        postService.update(postDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        postService.deleteById(id);
    }
}
