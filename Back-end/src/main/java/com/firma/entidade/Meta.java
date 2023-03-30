package com.firma.entidade;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Meta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_meta")
    private Long id;

    @Column(name = "criado_em")
    private Date criadoEm;

    @Column(name = "alterado_em")
    private Date alteradoEm;

    @Column(name = "criado_por")
    private Pessoa criadoPor;
}
