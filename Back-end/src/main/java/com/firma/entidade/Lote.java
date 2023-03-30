package com.firma.entidade;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lote")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_meta")
    private Meta meta;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Column(name = "quantidade_comprada")
    private long quantidadeComprada;

    @Column(name = "quantidade_atual")
    private long quantidadeAtual;

    @Column(name = "comprado_em")
    private Date compradoEm;
}
