package com.ctfera.proposta_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Usando lombok para criação de construtores, gets e sets na hora do build
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropostaRequestDTO {
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private Double renda;
    private Double valorSolicitado;
    private int prazoPagamento;
}
