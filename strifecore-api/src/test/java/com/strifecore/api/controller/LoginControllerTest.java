package com.strifecore.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strifecore.api.dto.LoginDto;
import com.strifecore.core.security.AuthenticationDto;
import com.strifecore.core.service.AuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    private AuthenticationDto authenticationDto;

    private MockMvc mockMvc;

    @InjectMocks
    private LoginController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        authenticationDto = new AuthenticationDto("TestUser", "TestUser:1000:123123123123", false);

        when(authenticationService.authenticate("TestUser", "1234567890")).thenReturn(authenticationDto);
        when(authenticationService.authenticate("TestUser", "wrongpassword")).thenThrow(new BadCredentialsException(""));

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testLogin() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("TestUser");
        loginDto.setPassword("1234567890");
        byte[] loginDtoJson = objectMapper.writeValueAsBytes(loginDto);

        mockMvc.perform(
                    post("/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(loginDtoJson)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"TestUser\",\"token\":\"TestUser:1000:123123123123\",\"admin\":false}"));
    }

    @Test
    public void testLoginWithWrongPassword() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("TestUser");
        loginDto.setPassword("wrongpassword");
        byte[] loginDtoJson = objectMapper.writeValueAsBytes(loginDto);

        mockMvc.perform(
                post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginDtoJson)
        )
                .andExpect(status().is(401))
                .andExpect(content().string("Bad Credentials"));
    }
}
