package pe.gob.pnsu.controlasistenciaws.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pe.gob.pnsu.controlasistenciaws.dto.RequestParcialDto;
import pe.gob.pnsu.controlasistenciaws.service.IAsistenciaParcial;
import pe.gob.pnsu.controlasistenciaws.util.RestResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Slf4j
@RestController
public class ControlAsistenciaController {

  //  @Autowired
  //  IAsistenciaParcial oAsistenciaParcial;

/*
    @RequestMapping(value = "/registrarcontrolasistenciaparcial", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Map<String,Object> registrarcontrolasistenciaparcial(@Valid @RequestBody RequestParcialDto oRequestDto){
        Map<String, Object> map = new HashMap<>();



        RestResponse rpta = oAsistenciaParcial.registrarcontrolasistenciaparcial(
                Optional.ofNullable(oRequestDto.getIdpersonal()).isPresent() ? oRequestDto.getIdpersonal() : "",
                Optional.ofNullable(oRequestDto.getDocumentoidentidad()).isPresent() ? oRequestDto.getDocumentoidentidad() : "",
                oRequestDto.getDesde(), oRequestDto.getHasta());

        map.put("STATUS",rpta.getCodigo());
        map.put("MESSAGE",rpta.getMensaje());

        map.put("Hola","jose");
        return map;
    }
*/


}
