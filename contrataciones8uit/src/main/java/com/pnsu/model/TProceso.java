package com.pnsu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="T_PROCESO")
public class TProceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPROCESO")
    private Integer idproceso;

    @Column(name="EXPEDIENTE")
    private String expediente;

    @Column(name="DESCRIPCION")
    private String descripcion;

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
