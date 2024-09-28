package com.ctfera.java_spb.mapper;

import com.ctfera.java_spb.dto.PropostaRequestDTO;
import com.ctfera.java_spb.dto.PropostaResponseDTO;
import com.ctfera.java_spb.entity.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


// Após criação da estrutura básica, foi usado o complie do mavem para criar a implementação do PropostaMapper
@Mapper
public interface PropostaMapper {

    @Mapping(target = "usuario.nome", source = "nome") //Mapeando campo final <- origemDTO
    @Mapping(target = "usuario.sobrenome", source = "sobrenome") //Mapeando campo final <- origemDTO
    @Mapping(target = "usuario.cpf", source = "cpf") //Mapeando campo final <- origemDTO
    @Mapping(target = "usuario.telefone", source = "telefone") //Mapeando campo final <- origemDTO
    @Mapping(target = "usuario.renda", source = "renda") //Mapeando campo final <- origemDTO
    @Mapping(target = "id", ignore=true) //ignorar mapeamento não presente no DTO
    @Mapping(target = "aprovada", ignore=true) //ignorar mapeamento não presente no DTO
    @Mapping(target = "integrada", ignore=true) //ignorar mapeamento não presente no DTO
    @Mapping(target = "observacao", ignore=true) //ignorar mapeamento não presente no DTO
    Proposta convertDtoToProposta(PropostaRequestDTO propostaRequestDTO);


    PropostaResponseDTO convertEntityToDto(Proposta proposta);
}
