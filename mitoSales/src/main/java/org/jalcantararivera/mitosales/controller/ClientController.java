package org.jalcantararivera.mitosales.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jalcantararivera.mitosales.dto.ClientDTO;
import org.jalcantararivera.mitosales.dto.GenericResponse;
import org.jalcantararivera.mitosales.model.Client;
import org.jalcantararivera.mitosales.service.IClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor

public class ClientController {

    //private final ClientServiceImpl service;
    private final IClientService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>>  readAll() throws  Exception{
        //ModelMapper modelMapper= new ModelMapper();
        List<ClientDTO>list=service.readAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ClientDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Client obj= service.readById(id);
        return ResponseEntity.ok(new GenericResponse<>(200,"SUCCESS", Arrays.asList(convertToDto(obj))));
    }
    @PostMapping
    public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientDTO dto) throws  Exception{
        Client obj= service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
        //return new ResponseEntity.created();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO dto,@PathVariable("id") Integer id ) throws Exception{
        Client obj= service.update(convertToEntity(dto),id);
        return ResponseEntity.ok(convertToDto(obj));
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id ) throws Exception{
        service.delete(id);
        //return ResponseEntity.noContent().build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    ////////////////////////////////////////////////////
    private ClientDTO convertToDto(Client obj){
        return modelMapper.map(obj,ClientDTO.class);
    }
    private Client convertToEntity(ClientDTO dto){
        return modelMapper.map(dto,Client.class);
    }


}
