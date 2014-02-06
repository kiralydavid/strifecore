package com.strifecore.api.controller;

import com.strifecore.core.service.TestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class HelloControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TestService testService;

    @InjectMocks
    private HelloController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(testService.hello("StrifeCore")).thenReturn("Hello StrifeCore!");

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testHello() throws Exception {

        mockMvc.perform(get("/hello/StrifeCore"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hello StrifeCore!"));
    }
}
