package org.jalcantararivera.mitosales.controller;

import org.jalcantararivera.mitosales.dto.CategoryDTO;
import org.jalcantararivera.mitosales.model.Category;
import org.jalcantararivera.mitosales.service.ICategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;


import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

     @Autowired
     private MockMvc mockMvc;

     @MockBean
     private ICategoryService service;

     @MockBean(name="categoryMapper")
     private ModelMapper modelMapper;

     Category CATEGORY_1 = new Category(1,"TV","Television",true);
    Category CATEGORY_2 = new Category(2,"PSP","Play Station",true);
    Category CATEGORY_3 = new Category(3,"BOOKS","Some books",true);

    CategoryDTO CATEGORYDTO_1= new CategoryDTO(1,"TV","Television",true);
    @Test
    void readAllTest() throws Exception{

        List<Category> categories = List.of(CATEGORY_1,CATEGORY_2,CATEGORY_3);
        Mockito.when(service.readAll()).thenReturn(categories);
        Mockito.when(modelMapper.map(CATEGORY_1, CategoryDTO.class)).thenReturn(CATEGORYDTO_1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/categories")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].nameofCategory",is("TV")));
    }

    @Test
    void readByIdTest() throws Exception{
        final int ID=1;

        Mockito.when(service.readById(any())).thenReturn(CATEGORY_1);
        Mockito.when(modelMapper.map(CATEGORY_1,CategoryDTO.class)).thenReturn(CATEGORYDTO_1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/categories"+ID)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameofCategory",is("TVE")));

    }

}
