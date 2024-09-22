package com.ctfera.java_spb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Geração de chaves primárias fica à cargo do SGBD
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private Double renda;

    @OneToOne(mappedBy = "usuario") //Relacionamento 1x1 com Proposta, mapeando usuario como nome do campo referente ao objeto Usuario em Proposta
    private Proposta proposta;
}
