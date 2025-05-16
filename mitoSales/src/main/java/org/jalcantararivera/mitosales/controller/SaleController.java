package org.jalcantararivera.mitosales.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jalcantararivera.mitosales.dto.GenericResponse;
import org.jalcantararivera.mitosales.dto.IProcedureDTO;
import org.jalcantararivera.mitosales.dto.ProcedureDTO;
import org.jalcantararivera.mitosales.dto.SaleDTO;
import org.jalcantararivera.mitosales.model.Sale;
import org.jalcantararivera.mitosales.service.ISaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    //private final SaleServiceImpl service;
    private final ISaleService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SaleDTO>>  readAll() throws  Exception{
        //ModelMapper modelMapper= new ModelMapper();
        List<SaleDTO>list=service.readAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<SaleDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Sale obj= service.readById(id);
        return ResponseEntity.ok(new GenericResponse<>(200,"SUCCESS", Arrays.asList(convertToDto(obj))));
    }
    @PostMapping
    public ResponseEntity<SaleDTO> save(@Valid @RequestBody SaleDTO dto) throws  Exception{
        Sale obj= service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
        //return new ResponseEntity.created();
    }
    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> update(@Valid @RequestBody SaleDTO dto,@PathVariable("id") Integer id ) throws Exception{
        Sale obj= service.update(convertToEntity(dto),id);
        return ResponseEntity.ok(convertToDto(obj));
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id ) throws Exception{
        service.delete(id);
        //return ResponseEntity.noContent().build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    ////////////////////////////////////////////////////
    private SaleDTO convertToDto(Sale obj){
        return modelMapper.map(obj,SaleDTO.class);
    }
    private Sale convertToEntity(SaleDTO dto){
        return modelMapper.map(dto,Sale.class);
    }

    ////////////////////////////////////////////////////

    @GetMapping("/resume")
    public ResponseEntity<List<ProcedureDTO>> getSaleResume() throws Exception{
        List<ProcedureDTO> list = service.callProcedure();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/resume2")
    public ResponseEntity<List<IProcedureDTO>> getSaleResume2() throws Exception{
        //List<IProcedureDTO> list = service.callProcedure2();
        //return ResponseEntity.ok(list);
        return new ResponseEntity<>(service.callProcedure2(),HttpStatus.OK);
    }

    @GetMapping("/resume3")
    public ResponseEntity<List<ProcedureDTO>> getSaleResume3() {
        return new ResponseEntity<>(service.callProcedure3(),HttpStatus.OK);
    }

}
