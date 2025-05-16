package com.pnsu.controller;

import com.pnsu.crud.Impl.ProcesoCrud;
import com.pnsu.dto.TProcesoDto;
import com.pnsu.model.TProceso;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ContratacionesController {

    private final ProcesoCrud procesoCrud;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping("/")
    public String contrataciones() {
        return "API REST CONTRATACIONESWS";
    }

    @GetMapping("/listar")
    public List<TProcesoDto> listar() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        List<TProcesoDto> list = procesoCrud.readAll().stream().
                map(this::convertToDto).
                map(e -> {
                    e.setEstado(now.isBefore(e.getFechavencimiento()) ? "Activo" : "Inactivo");
                    return e;
                }).
                collect(Collectors.toList());
        return list;

    }

    ////////////////////////////////////////////////////
    private TProcesoDto convertToDto(TProceso obj) {
        return modelMapper.map(obj, TProcesoDto.class);
    }
    /*
    private Category convertToEntity(CategoryDTO dto){
        return modelMapper.map(dto,Category.class);
    }
    */
    ////////////////////////////////////////////////////


}
