package org.petrov.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.petrov.dto.PostDto;
import org.petrov.dto.TagDto;
import org.petrov.service.TagService;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TagControllerTest {

    @InjectMocks
    private TagController tagController;

    @Mock
    private TagService tagService;

    private MockMvc mockMvc;
    private final Gson gson = new Gson();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();
    }

    @Test
    public void testGetTagsByPost() throws Exception {
        PostDto postDto = new PostDto();
        List<TagDto> tagList = new ArrayList<>();

        Mockito.when(tagService.findTagsByPost(postDto)).thenReturn(tagList);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.get("/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(tagList))
        ).andExpect(status().isOk()).andReturn().getResponse();

        verify(tagService).findTagsByPost(postDto);

    }

    @Test
    public void testAddTagToPost() throws Exception {
        PostDto postDto = new PostDto();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(postDto))
        ).andExpect(status().isOk());

        verify(tagService).addTagToPost(postDto);
    }

    @Test
    public void testUpdateTagForPost() throws Exception {
        PostDto postDto = new PostDto();
        postDto.setId(1L);

        mockMvc.perform(MockMvcRequestBuilders.put("/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(postDto)))
                        .andExpect(status().isOk());

        verify(tagService).updateTagForPost(postDto);

    }

    @Test
    public void testRemoveTagFromPost() throws Exception {
        PostDto postDto = new PostDto();

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(postDto))
        ).andExpect(status().isOk());

        verify(tagService).removeTagFromPost(postDto);
    }
}
