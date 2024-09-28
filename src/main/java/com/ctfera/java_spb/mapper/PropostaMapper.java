package com.ctfera.java_spb.mapper;

import com.ctfera.java_spb.dto.PropostaRequestDTO;
import com.ctfera.java_spb.dto.PropostaResponseDTO;
import com.ctfera.java_spb.entity.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


// Após criação da estrutura básica, foi usado o complie do mavem para criar a implementação do PropostaMapper
@Mapper
public interface PropostaMapper {

    //Método para chamar a interface de mapeamento
    PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class);


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

    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "sobrenome", source = "usuario.sobrenome")
    @Mapping(target = "telefone", source = "usuario.telefone")
    @Mapping(target = "cpf", source = "usuario.cpf")
    @Mapping(target = "renda", source = "usuario.renda")
    PropostaResponseDTO convertEntityToDto(Proposta proposta);
}
