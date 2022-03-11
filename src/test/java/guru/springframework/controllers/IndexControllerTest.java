package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    IndexController controller;
    MockMvc mockedMvc;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        controller = new IndexController(recipeService);
        mockedMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetIndexPage() throws Exception {
        mockedMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}