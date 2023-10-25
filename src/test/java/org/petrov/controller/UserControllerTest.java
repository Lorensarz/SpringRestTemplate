package org.petrov.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.petrov.config.AppConfig;
import org.petrov.dto.UserDto;
import org.petrov.entity.UserEntity;
import org.petrov.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AppConfig.class})
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private final Gson gson = new Gson();


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetUserById() throws Exception {
        UserDto userDto = new UserDto();

        when(userService.findById(anyLong())).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(userDto)));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<UserEntity> userEntities = new ArrayList<>();
        UserEntity userEntity1 = new UserEntity();
        UserEntity userEntity2 = new UserEntity();

        userEntities.add(userEntity1);
        userEntities.add(userEntity2);

        when(userService.findAll()).thenReturn(userEntities);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(userEntities)));
    }

    @Test
    public void testCreateUser() throws Exception {
        UserDto userDto = new UserDto();

        doNothing().when(userService).save(userDto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userDto));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserDto userDto = new UserDto();

        doNothing().when(userService).update(userDto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userDto));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteUser() throws Exception {
        long userId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(userService).deleteById(userId);
    }
}
