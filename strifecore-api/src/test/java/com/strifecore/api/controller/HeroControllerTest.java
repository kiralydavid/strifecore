package com.strifecore.api.controller;

import com.strifecore.api.config.TestContext;
import com.strifecore.api.config.WebContext;
import com.strifecore.core.config.RootContext;
import com.strifecore.core.domain.*;
import com.strifecore.core.service.HeroService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootContext.class, TestContext.class, WebContext.class})
@TransactionConfiguration(defaultRollback = true)
public class HeroControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    private MockMvc mockMvc;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Mock
    private HeroService heroService;

    @InjectMocks
    private HeroController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Hero hero = new HeroBuilder()
                .setName("Caprice")

                .addAttribute(AttributeName.ATTACK, new ScalingAttribute(54D, 2.48, 15))
                .addAttribute(AttributeName.ATTACK_SPEED, new StaticAttribute(0.9))
                .addAttribute(AttributeName.MANA, new ScalingAttribute(240D, 10D, 15))
                .addAttribute(AttributeName.MANA_REGEN, new StaticAttribute(3.7))
                .addAttribute(AttributeName.HEALTH, new ScalingAttribute(603D, 41D, 15))
                .addAttribute(AttributeName.HEALTH_REGEN, new ScalingAttribute(1.8, 0.12, 15))
                .addAttribute(AttributeName.MOVEMENT_SPEED, new StaticAttribute(330D))
                .addAttribute(AttributeName.ARMOR, new ScalingAttribute(10D, 1.5, 15))
                .addAttribute(AttributeName.MAGIC_ARMOR, new ScalingAttribute(10D, 1.5, 15))
                .addAttribute(AttributeName.ATTACK_RANGE, new StaticAttribute(500D))

                .addSkill(new SkillBuilder()
                        .setName("Fire Lager")
                        .setDescription("Target direction to deal 70/90/110/130 Magic damage in a line. Line explodes after 0.9 seconds, dealing half damage.")
                        .setSlot(SkillSlot.FIRST)
                        .addAttribute(AttributeName.MANA_COST, new ScalingAttribute(70D, 10D, 4))
                        .addAttribute(AttributeName.COOLDOWN, new StaticAttribute(9D))
                        .addAttribute(AttributeName.RANGE, new StaticAttribute(650D))
                        .addAttribute(AttributeName.RADIUS, new StaticAttribute(180D))
                        .build()
                )
                .addSkill(new SkillBuilder()
                        .setName("Anchors Aweigh")
                        .setDescription("Target location to deal 70/95/120/145 Magic damage and stun enemies for 0.7/0.9/1.1/1.3 seconds.")
                        .setSlot(SkillSlot.SECOND)
                        .addAttribute(AttributeName.MANA_COST, new ScalingAttribute(70D, 10D, 4))
                        .addAttribute(AttributeName.COOLDOWN, new StaticAttribute(11D))
                        .addAttribute(AttributeName.RANGE, new StaticAttribute(550D))
                        .addAttribute(AttributeName.RADIUS, new StaticAttribute(200D))
                        .build()
                )
                .addSkill(new SkillBuilder()
                        .setName("Volatility")
                        .setDescription("Every third source of damage you apply to a target combusts, dealing 30/45/60/75 bonus Magic damage.")
                        .setSlot(SkillSlot.THIRD)
                        .build()
                )
                .addSkill(new SkillBuilder()
                        .setName("Quick Draw")
                        .setDescription("Target location to leap there, dealing 90/120/150 Magic damage to the nearest enemy. Prioritizes heroes.")
                        .setSlot(SkillSlot.ULTI)
                        .addAttribute(AttributeName.MANA_COST, new ScalingAttribute(90D, 10D, 3))
                        .addAttribute(AttributeName.COOLDOWN, new StaticAttribute(40D))
                        .addAttribute(AttributeName.RANGE, new StaticAttribute(600D))
                        .build()
                )
                .build();

        when(heroService.getById(1)).thenReturn(hero);
        when(heroService.getById(999)).thenReturn(null);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(jacksonMessageConverter).build();
    }

    @Test
    public void testGetHero() throws Exception {
        MvcResult result = mockMvc.perform(get("/hero/1"))
                .andExpect(status().isOk())
                .andReturn();

        String expectedJson = "{\"id\":null,\"attributes\":{\"MOVEMENT_SPEED\":{\"value\":330.0,\"type\":1},\"MANA_REGEN\":" +
                "{\"value\":3.7,\"type\":1},\"MANA\":{\"value\":240.0,\"increasePerLevel\":10.0,\"maxLevel\":15,\"type\":2}," +
                "\"ATTACK_RANGE\":{\"value\":500.0,\"type\":1},\"ATTACK\":{\"value\":54.0,\"increasePerLevel\":2.48,\"maxLevel" +
                "\":15,\"type\":2},\"HEALTH\":{\"value\":603.0,\"increasePerLevel\":41.0,\"maxLevel\":15,\"type\":2},\"MAGIC_ARMOR\"" +
                ":{\"value\":10.0,\"increasePerLevel\":1.5,\"maxLevel\":15,\"type\":2},\"ARMOR\":{\"value\":10.0,\"increasePerLevel" +
                "\":1.5,\"maxLevel\":15,\"type\":2},\"HEALTH_REGEN\":{\"value\":1.8,\"increasePerLevel\":0.12,\"maxLevel\":15,\"type\":2}," +
                "\"ATTACK_SPEED\":{\"value\":0.9,\"type\":1}},\"name\":\"Caprice\",\"skills\":[{\"name\":\"Fire Lager\",\"description\":\"Target" +
                " direction to deal 70/90/110/130 Magic damage in a line. Line explodes after 0.9 seconds, dealing half damage.\",\"attributes\":" +
                "{\"MANA_COST\":{\"value\":70.0,\"increasePerLevel\":10.0,\"maxLevel\":4,\"type\":2},\"COOLDOWN\":{\"value\":9.0,\"type\":1},\"" +
                "RANGE\":{\"value\":650.0,\"type\":1},\"RADIUS\":{\"value\":180.0,\"type\":1}},\"slot\":\"FIRST\"},{\"name\":\"Anchors Aweigh\",\"" +
                "description\":\"Target location to deal 70/95/120/145 Magic damage and stun enemies for 0.7/0.9/1.1/1.3 seconds.\",\"attributes\":" +
                "{\"MANA_COST\":{\"value\":70.0,\"increasePerLevel\":10.0,\"maxLevel\":4,\"type\":2},\"COOLDOWN\":{\"value\":11.0,\"type\":1},\"R" +
                "ANGE\":{\"value\":550.0,\"type\":1},\"RADIUS\":{\"value\":200.0,\"type\":1}},\"slot\":\"SECOND\"},{\"name\":\"Volatility\",\"descript" +
                "ion\":\"Every third source of damage you apply to a target combusts, dealing 30/45/60/75 bonus Magic damage.\",\"attributes\":{},\"slot" +
                "\":\"THIRD\"},{\"name\":\"Quick Draw\",\"description\":\"Target location to leap there, dealing 90/120/150 Magic damage to the nearest en" +
                "emy. Prioritizes heroes.\",\"attributes\":{\"MANA_COST\":{\"value\":90.0,\"increasePerLevel\":10.0,\"maxLevel\":3,\"type\":2},\"COOLDOW" +
                "N\":{\"value\":40.0,\"type\":1},\"RANGE\":{\"value\":600.0,\"type\":1}},\"slot\":\"ULTI\"}]}";

        JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testGetHeroNotFound() throws Exception {
        MvcResult result = mockMvc.perform(get("/hero/999"))
                .andExpect(status().is(404))
                .andReturn();

        JSONAssert.assertEquals("{\"errorCode\": 1001, \"message\": \"Hero not found.\"}", result.getResponse().getContentAsString(), false);
    }
}
