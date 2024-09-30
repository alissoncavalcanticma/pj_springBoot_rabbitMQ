package com.ctfera.java_spb.entity;

import jakarta.persistence.*;
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


    @OneToOne(cascade = CascadeType.PERSIST) //Relacionamento 1x1 com Usuario // (cascade = CascadeType.PERSIST) faz o usuário relacionado à proposta, caso não exista, ser salvo no banco antes da proposta
    @JoinColumn(name = "id_usuario") //Mapeando id_usuario como chave estrangeira de Usuario
    private Usuario usuario;


}
