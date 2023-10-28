package org.petrov.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.petrov.dto.TagDto;
import org.petrov.dto.mapper.PostDtoMapper;
import org.petrov.dto.mapper.TagDtoMapper;
import org.petrov.service.TagService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockitoSettings
public class TagControllerTest {

    @InjectMocks
    private TagController tagController;

    @Mock
    private TagService tagService;

    private MockMvc mockMvc;
    private final Gson gson = new Gson();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();
    }

    @Test
    public void testGetTagsByPost() throws Exception {
        List<TagDto> tagDtosList = new ArrayList<>();
        TagDto tagDto1 = new TagDto();
        TagDto tagDto2 = new TagDto();
        tagDtosList.add(tagDto1);
        tagDtosList.add(tagDto2);

        when(tagService.findTagsByPostId(anyLong())).thenReturn(tagDtosList);

        mockMvc.perform(MockMvcRequestBuilders.get("/tags/{postId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(tagDtosList))
        ).andExpect(status().isOk()).andReturn().getResponse();

        verify(tagService).findTagsByPostId(anyLong());
    }

    @Test
    public void testAddTagToPost() throws Exception {
        TagDto tagDto = new TagDto();
        tagDto.setId(1L);
        tagDto.setName("Tag 1");

        mockMvc.perform(MockMvcRequestBuilders.post("/tags/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(tagDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testRemoveTagFromPost() throws Exception {
        TagDto tagDto = new TagDto();
        tagDto.setId(1L);
        tagDto.setName("Tag 1");

        mockMvc.perform(MockMvcRequestBuilders.delete("/tags/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(tagDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
