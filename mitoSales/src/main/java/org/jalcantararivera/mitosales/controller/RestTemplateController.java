package org.jalcantararivera.mitosales.controller;

import lombok.RequiredArgsConstructor;
import org.jalcantararivera.mitosales.dto.CategoryDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestTemplateController {
    private final RestTemplate restTemplate;

    @GetMapping("/pokemon/{name}")
    public ResponseEntity <?> getPokemon(@PathVariable("name") String name){
        String pokemonUrl= "https://pokeapi.co/api/v2/pokemon/"+name;
        String response= restTemplate.getForEntity(pokemonUrl,String.class).getBody();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test1")
    public ResponseEntity<List<CategoryDTO>> test1(){
        String url="http://localhost:8080/categories";
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity= new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<List<CategoryDTO>> typeRef=new ParameterizedTypeReference<>(){};
        return restTemplate.exchange(url, HttpMethod.GET,entity,typeRef);
    }

    @GetMapping("test2")
    public ResponseEntity<?> test2(
            @RequestParam(name="page" ,defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "10") int size
    ){
        String url= "http://localhost:8080/categories/pagination2?p"+page+"&s="+size;
        ResponseEntity<?> response=restTemplate.getForEntity(url,String.class);
        return ResponseEntity.ok(response.getBody());
    }


    @GetMapping("test3")
    public ResponseEntity<?> test3(
            @RequestParam(name="page" ,defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "10") int size
    ){
        String url= "http://localhost:8080/categories/pagination2?p={page}&s={size}";
        Map<String,Integer> uriVariables= new HashMap<>();
        uriVariables.put("page",page);
        uriVariables.put("size",size);
        HttpHeaders httpHeaders= new HttpHeaders();
        HttpEntity requestEntity= new HttpEntity(httpHeaders);
        return restTemplate.exchange(url,HttpMethod.GET,requestEntity,Map.class,uriVariables);
    }
}
