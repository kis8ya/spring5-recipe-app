package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class RecipesControllerTest {

    @Mock
    RecipeService service;

    @InjectMocks
    RecipesController controller;

    MockMvc mockedMvc;

    @BeforeEach
    void setUp() {
        mockedMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getRecipe() throws Exception {
        Mockito.when(service.getById(any())).thenReturn(
                Optional.of(Recipe.builder().id(77L).description("Tomato").build())
        );

        mockedMvc.perform(get("/recipes/77"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/get"));
    }

    @Test
    void getRecipeNotFound() throws Exception {
        Mockito.when(service.getById(any())).thenReturn(
                Optional.empty()
        );

        mockedMvc.perform(get("/recipes/77"))
                .andExpect(status().isOk())
                .andExpect(view().name("errors/not-found"));
    }
}
