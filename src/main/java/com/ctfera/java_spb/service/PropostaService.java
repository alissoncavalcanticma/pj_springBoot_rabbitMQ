package com.ctfera.java_spb.service;

import com.ctfera.java_spb.dto.PropostaRequestDTO;
import com.ctfera.java_spb.dto.PropostaResponseDTO;
import com.ctfera.java_spb.entity.Proposta;
import com.ctfera.java_spb.mapper.PropostaMapper;
import com.ctfera.java_spb.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor //Anotação Spring que usa o Lombok para fazer a injeção de dependências.
@Service //Anotação de camada service para definir a responsabilidade de gerência do Spring
public class PropostaService {

    private PropostaRepository propostaRepository;

    public PropostaResponseDTO criar (PropostaRequestDTO requestDTO){
        Proposta proposta =  PropostaMapper.INSTANCE.convertDtoToProposta(requestDTO);
        propostaRepository.save(proposta);
        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    public List<PropostaResponseDTO> obterProposta() {
        return PropostaMapper.INSTANCE.convertListEntityToListDto( propostaRepository.findAll() );
    }
}
