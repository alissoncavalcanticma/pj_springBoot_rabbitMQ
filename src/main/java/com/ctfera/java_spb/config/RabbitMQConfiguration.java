package com.ctfera.java_spb.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

        //Criação das filas
        //Filas foram criadas nesse microserviço, mas deveriam ser criadas de acordo com a responsabilidade de cada microserviço.


        @Bean
        public Queue criarFilaPropostaPendenteMsAnaliseCredito(){
            return QueueBuilder.durable("proposta-pendente.ms-analise-credito").build();
        }

        @Bean
        public Queue criarFilaPropostaPendenteMsNotificacao(){
            return QueueBuilder.durable("proposta-pendente.ms-notificacao").build();
        }

        @Bean
        public Queue criarFilaPropostaConcluidaMsProposta(){
            return QueueBuilder.durable("proposta-concluida.ms-proposta").build();
        }

        @Bean
        public Queue criarFilaPropostaConcluidaMsNotificacao(){
            return QueueBuilder.durable("proposta-concluida.ms-notificacao").build();
        }
}
