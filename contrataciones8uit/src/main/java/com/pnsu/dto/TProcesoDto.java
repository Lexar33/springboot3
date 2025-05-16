package com.pnsu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TProcesoDto {

    @Column(name = "IDPROCESO")
    private Integer idproceso;

    @Column(name="EXPEDIENTE")
    private String expediente;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name="ESTADO")
    private String estado;

    @Column(name="FECHAPUBLICACION")
    private LocalDateTime fechapublicacion;

    @Column(name="FECHAVENCIMIENTO")
    private LocalDateTime fechavencimiento;

    @Column(name="FECHAAMPLIACION")
    private LocalDateTime fechaampliacion;

    @Column(name="CONTACTO")
    private String contacto;

    @Column(name="TDR")
    private String tdr;

    @Column(name="ANEXO")
    private String anexo;
}
