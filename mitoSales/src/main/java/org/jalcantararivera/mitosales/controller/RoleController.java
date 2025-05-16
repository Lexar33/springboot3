package org.jalcantararivera.mitosales.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jalcantararivera.mitosales.dto.GenericResponse;
import org.jalcantararivera.mitosales.dto.RoleDTO;
import org.jalcantararivera.mitosales.model.Role;
import org.jalcantararivera.mitosales.service.IProviderService;
import org.jalcantararivera.mitosales.service.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor

public class RoleController {

    //private final RoleServiceImpl service;
    private final IRoleService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<RoleDTO>>  readAll() throws  Exception{
        //ModelMapper modelMapper= new ModelMapper();
        List<RoleDTO>list=service.readAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<RoleDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Role obj= service.readById(id);
        return ResponseEntity.ok(new GenericResponse<>(200,"SUCCESS", Arrays.asList(convertToDto(obj))));
    }
    @PostMapping
    public ResponseEntity<RoleDTO> save(@Valid @RequestBody RoleDTO dto) throws  Exception{
        Role obj= service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
        //return new ResponseEntity.created();
    }
    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@Valid @RequestBody RoleDTO dto,@PathVariable("id") Integer id ) throws Exception{
        Role obj= service.update(convertToEntity(dto),id);
        return ResponseEntity.ok(convertToDto(obj));
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id ) throws Exception{
        service.delete(id);
        //return ResponseEntity.noContent().build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    ////////////////////////////////////////////////////
    private RoleDTO convertToDto(Role obj){
        return modelMapper.map(obj,RoleDTO.class);
    }
    private Role convertToEntity(RoleDTO dto){
        return modelMapper.map(dto,Role.class);
    }


}
