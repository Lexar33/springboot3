package org.jalcantararivera.mitosales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(IngressDetailPK.class)
public class IngressDetail {
    //LLAVE PRIMARIA COMPUESTA
    @Id
    private Ingress ingress;
    @Id
    private Product product;
    /* ENFOQUE CLASICO CON LLAVES FORANEAS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngressDetail;

    @ManyToOne
    private Ingress ingress;

    @ManyToOne
    private Product product;
    */
}
