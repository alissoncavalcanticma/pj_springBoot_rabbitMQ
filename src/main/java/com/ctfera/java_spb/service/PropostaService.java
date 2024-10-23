package com.ctfera.java_spb.service;

import com.ctfera.java_spb.dto.PropostaRequestDTO;
import com.ctfera.java_spb.dto.PropostaResponseDTO;
import com.ctfera.java_spb.entity.Proposta;
import com.ctfera.java_spb.mapper.PropostaMapper;
import com.ctfera.java_spb.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

//@AllArgsConstructor //Anotação Spring que usa o Lombok para fazer a injeção de dependências. //Commentado e substituído por construtor
@Service //Anotação de camada service para definir a responsabilidade de gerência do Spring
public class PropostaService {

    private PropostaRepository propostaRepository;

    private NotificacaoService notificacaoService;

    //Por causa de ser um valor de propriedade, não será usado um Bean, mas sim o construtor.
    private String exchange;

    public PropostaService(PropostaRepository propostaRepository,
                           NotificacaoService notificacaoService,
                           @Value("${spring.rabbitmq.propostapendente.exchange}")String exchange) {

        this.propostaRepository = propostaRepository;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;

    }

    public PropostaResponseDTO criar (PropostaRequestDTO requestDTO){
        Proposta proposta =  PropostaMapper.INSTANCE.convertDtoToProposta(requestDTO);
        propostaRepository.save(proposta);

        // PropostaResponseDTO response = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
        // notificacaoService.notificar(response, exchange);

        notificarRabbitMQ(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    private void notificarRabbitMQ(Proposta proposta){
        notificacaoService.notificar(proposta, exchange);
    }


    public List<PropostaResponseDTO> obterProposta() {
        return PropostaMapper.INSTANCE.convertListEntityToListDto( propostaRepository.findAll() );
    }
}
