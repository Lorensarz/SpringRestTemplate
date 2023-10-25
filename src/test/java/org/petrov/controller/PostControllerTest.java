package org.petrov.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.petrov.entity.PostEntity;
import org.petrov.entity.TagEntity;
import org.petrov.service.PostService;
import org.petrov.dto.PostDto;
import org.petrov.dto.mapper.PostDtoMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;


public class PostControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private PostDtoMapper postDtoMapper;
    @InjectMocks
    private PostController postController;

    private final Gson gson = new Gson();

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void testGetPostsByUserId() throws Exception {
        List<PostEntity> posts = new ArrayList<>();
        PostEntity post1 = new PostEntity();


        TagEntity tagEntity1 = new TagEntity();

        post1.setTags(Collections.singletonList(tagEntity1));
        posts.add(post1);


        PostEntity post2 = new PostEntity();

        TagEntity tagEntity2 = new TagEntity();

        post2.setTags(Collections.singletonList(tagEntity2));
        posts.add(post2);

        when(postService.findPostsByUserId(anyLong())).thenReturn(posts);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts?user_id=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(posts)));
    }

    @Test
    public void testGetAllPosts() throws Exception {
        List<PostEntity> posts = new ArrayList<>();
        PostEntity post1 = new PostEntity();
        post1.setId(1L);
        posts.add(post1);

        PostEntity post2 = new PostEntity();
        post2.setId(2L);
        posts.add(post2);

        when(postService.findAll()).thenReturn(posts);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(posts)));
    }

    @Test
    public void testAddPost() throws Exception {
        PostDto postDto = new PostDto();


       doNothing().when(postService).save(postDto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(postDto));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdatePost() throws Exception {
        PostDto postDto = new PostDto();


        doNothing().when(postService).update(postDto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(postDto));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeletePost() throws Exception {
        long postId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/posts/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(postService).deleteById(postId);
    }
}
