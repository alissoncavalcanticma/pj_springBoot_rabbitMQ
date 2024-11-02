package com.ctfera.java_spb.agendador;

//Classe para buscar no banco de dados as propostas sem integração e enviar novamente para a fila

import com.ctfera.java_spb.entity.Proposta;
import com.ctfera.java_spb.repository.PropostaRepository;
import com.ctfera.java_spb.service.NotificacaoRabbitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropostaSemIntegracao {

    private PropostaRepository propostaRepository;

    private NotificacaoRabbitService notificacaoRabbitService;

    private String exchange;

    //Utilizando lib  org.slf4j
    public final Logger logger = LoggerFactory.getLogger(PropostaSemIntegracao.class);

    public PropostaSemIntegracao(PropostaRepository propostaRepository, NotificacaoRabbitService notificacaoRabbitService,@Value("${spring.rabbitmq.propostapendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchange = exchange;
    }

    public void buscarPropostasSemIntegracao(){
        propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {
           try{
               notificacaoRabbitService.notificar(proposta, exchange);

               atualizarProposta(proposta);
           }catch(RuntimeException ex){
                logger.error(ex.getMessage());
           }
        });
    }

    private void atualizarProposta(Proposta proposta){
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);
    }

}
