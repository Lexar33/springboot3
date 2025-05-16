package org.jalcantararivera.mitosales.config;

import org.jalcantararivera.mitosales.dto.CategoryDTO;
import org.jalcantararivera.mitosales.model.Category;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("categoryMapper")
    public ModelMapper categoryMapper(){
        ModelMapper mapper= new ModelMapper();
        //HANDLE MISSMATCHES
        //Lectura
        TypeMap<Category, CategoryDTO> typeMap1= mapper.createTypeMap(Category.class,CategoryDTO.class);
        typeMap1.addMapping(Category::getName,(dest,v)-> dest.setNameofCategory((String)v));
        //Escritura
        TypeMap<CategoryDTO, Category> typeMap2= mapper.createTypeMap(CategoryDTO.class,Category.class);
        typeMap2.addMapping(CategoryDTO::getNameofCategory,(dest,v)-> dest.setName((String)v));

        return mapper;
    }


    @Bean("defaultMapper")
    public ModelMapper modelMapper(){
        return new ModelMapper();

    }
}
