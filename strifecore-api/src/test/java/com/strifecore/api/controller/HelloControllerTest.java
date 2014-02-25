package com.strifecore.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strifecore.api.config.TestContext;
import com.strifecore.api.config.WebContext;
import com.strifecore.api.dto.LoginDto;
import com.strifecore.core.config.RootContext;
import com.strifecore.core.domain.User;
import com.strifecore.core.security.AuthenticationDto;
import com.strifecore.core.security.SaltedBCryptPasswordEncoder;
import com.strifecore.core.service.TestService;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootContext.class, TestContext.class, WebContext.class})
@TransactionConfiguration(defaultRollback = true)
public class HelloControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webContext;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    SaltedBCryptPasswordEncoder passwordEncoder;

    @Mock
    private TestService testService;

    @InjectMocks
    private HelloController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(testService.hello("StrifeCore")).thenReturn("Hello StrifeCore!");

        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void testHello() throws Exception {

        mockMvc.perform(get("/hello/StrifeCore"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hello StrifeCore!"));
    }

    @Test
    public void testSecuredHello() throws Exception {

        User user = new User();
        user.setName("TestUser");
        user.setEmail("test@test.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setActive(true);
        user.setAdmin(true);

        sessionFactory.getCurrentSession().save(user);

        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("TestUser");
        loginDto.setPassword("password");
        byte[] json = objectMapper.writeValueAsBytes(loginDto);

        MvcResult loginResult = mockMvc.perform(
                post("/login")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        AuthenticationDto authenticationDto = objectMapper.readValue(loginResult.getResponse().getContentAsString(), AuthenticationDto.class);

        mockMvc.perform(get("/hello/StrifeCore/secured")
                .header("X-Auth-Token", authenticationDto.getToken()))
                .andExpect(status().isOk())
                .andExpect(content().string("SecuredHello StrifeCore!"));
    }
}
