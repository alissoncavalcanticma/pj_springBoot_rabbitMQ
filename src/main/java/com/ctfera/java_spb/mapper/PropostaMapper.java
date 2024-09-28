package com.ctfera.java_spb.mapper;

import com.ctfera.java_spb.dto.PropostaRequestDTO;
import com.ctfera.java_spb.entity.Proposta;
import org.mapstruct.Mapper;


// Após criação da estrutura básica, foi usado o complie do mavem para criar a implementação do PropostaMapper
@Mapper
public interface PropostaMapper {

    Proposta convertDtoToProposta(PropostaRequestDTO propostaRequestDTO);
}
