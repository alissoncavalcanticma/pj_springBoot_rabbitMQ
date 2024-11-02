package com.ctfera.java_spb.agendador;

//Classe para buscar no banco de dados as propostas sem integração e enviar novamente para a fila

import com.ctfera.java_spb.entity.Proposta;
import com.ctfera.java_spb.repository.PropostaRepository;
import com.ctfera.java_spb.service.NotificacaoRabbitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PropostaSemIntegracao {

    private final PropostaRepository propostaRepository;

    private final NotificacaoRabbitService notificacaoRabbitService;

    private final String exchange;

    //Utilizando lib  org.slf4j
    public final Logger logger = LoggerFactory.getLogger(PropostaSemIntegracao.class);

    public PropostaSemIntegracao(PropostaRepository propostaRepository, NotificacaoRabbitService notificacaoRabbitService,@Value("${spring.rabbitmq.propostapendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchange = exchange;
    }

    //Annotation para Bean de agendamento com parâmetros de frequência de execução
    //Annotation @EnableScheduling deve ser acrescentado ao método main
    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void buscarPropostasSemIntegracao(){
        logger.info("Execução de JOB realizada."); //Gerar Log de execução do Job
        propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {
           try{
               notificacaoRabbitService.notificar(proposta, exchange);

               atualizarProposta(proposta);
               logger.info("Proposta pendente integrada na execução de JOB"); //Gerar Log de execução do Job
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
