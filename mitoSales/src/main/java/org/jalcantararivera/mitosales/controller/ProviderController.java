package org.jalcantararivera.mitosales.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jalcantararivera.mitosales.dto.GenericResponse;
import org.jalcantararivera.mitosales.dto.ProviderDTO;
import org.jalcantararivera.mitosales.model.Provider;
import org.jalcantararivera.mitosales.service.IProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor

public class ProviderController {

    private final IProviderService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProviderDTO>>  readAll() throws  Exception{
        List<ProviderDTO>list=service.readAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ProviderDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Provider obj= service.readById(id);
        return ResponseEntity.ok(new GenericResponse<>(200,"SUCCESS", Arrays.asList(convertToDto(obj))));
    }
    @PostMapping
    public ResponseEntity<ProviderDTO> save(@Valid @RequestBody ProviderDTO dto) throws  Exception{
        Provider obj= service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
        //return new ResponseEntity.created();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProviderDTO> update(@Valid @RequestBody ProviderDTO dto,@PathVariable("id") Integer id ) throws Exception{
        Provider obj= service.update(convertToEntity(dto),id);
        return ResponseEntity.ok(convertToDto(obj));
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id ) throws Exception{
        service.delete(id);
        //return ResponseEntity.noContent().build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    ////////////////////////////////////////////////////
    private ProviderDTO convertToDto(Provider obj){
        return modelMapper.map(obj,ProviderDTO.class);
    }
    private Provider convertToEntity(ProviderDTO dto){
        return modelMapper.map(dto,Provider.class);
    }


}
