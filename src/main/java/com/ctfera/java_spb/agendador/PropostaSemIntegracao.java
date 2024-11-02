package com.ctfera.java_spb.agendador;

//Classe para buscar no banco de dados as propostas sem integração e enviar novamente para a fila

import com.ctfera.java_spb.repository.PropostaRepository;
import com.ctfera.java_spb.service.NotificacaoRabbitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropostaSemIntegracao {

    private PropostaRepository propostaRepository;

    private NotificacaoRabbitService notificacaoRabbitService;

    private String exchange;

    public PropostaSemIntegracao(PropostaRepository propostaRepository, NotificacaoRabbitService notificacaoRabbitService,@Value("${spring.rabbitmq.propostapendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchange = exchange;
    }

    public void buscarPropostasSemIntegracao(){
        propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {
           try{
               notificacaoRabbitService.notificar(proposta, exchange);
               proposta.setIntegrada(true);
               propostaRepository.save(proposta);
           }catch(RuntimeException ex){
               System.out.println(ex);
           }
        });
    }

}
