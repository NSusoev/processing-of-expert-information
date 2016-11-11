package app.controller;

import app.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AppControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void mainPageTest() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h1>Методы ранжирования</h1>")));
    }

    @Test
    public void secondMethodTest() throws Exception {
        mockMvc.perform(get("/second/form"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h2>Индивидуальное ранжирование</h2>")));
    }

    @Test
    public void secondMethodSendDataTest() throws Exception {
        mockMvc.perform(post("/second/calc")
                .param("sequence", "2 3 4 2 1 5"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h2>Результат индивидуального ранжирования</h2>")));
    }

    @Test
    public void secondMethodSendInvalidDataTest() throws Exception {
        mockMvc.perform(post("/second/calc")
                .param("sequence", "2 3a 4 2 1 5"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Необходимо ввести только числовые значения")));
    }

    @Test
    public void secondMethodSendEmptyDataTest() throws Exception {
        mockMvc.perform(post("/second/calc")
                .param("sequence", ""))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Введите данные для ранжирования")));
    }

    @Test
    public void interactiveSecondMethodTest() throws Exception {
        mockMvc.perform(get("/second/interactive/form"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h2>Интерактивное индивидуальное ранжирование</h2>")));
    }

    @Test
    public void bordMethodTest() throws Exception {
        mockMvc.perform(get("/bord/form"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h2>Ранжирование методом Борда</h2>")));
    }

}
