package com.ctfera.java_spb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tb_proposta")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor")
    private Double valorSolicitado;
    private int prazoPagamento; //tipo primitivo
    private Boolean aprovada;
    private boolean integrada;  //tipo primitivo
    private String observacao;

    @OneToOne //Relacionamento 1x1 com Usuario
    @JoinColumn(name = "id_usuario") //Mapeando id_usuario como chave estrangeira de Usuario
    private Usuario usuario;


}
