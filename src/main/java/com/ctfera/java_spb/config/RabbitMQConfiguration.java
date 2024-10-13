package com.ctfera.java_spb.config;

//import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {

        //Criação das filas
        //Filas foram criadas nesse microserviço, mas deveriam ser criadas de acordo com a responsabilidade de cada microserviço.
        //@Bean é utilizado quando se quer passar a instância de response do método para a gerência do Spring
        //Obs: @Bean não passa o método, e sim o response dele para o Spring

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

        /*
        Criando configuration para connection factory

        private ConnectionFactory connectionFactory;

        public RabbitMQConfiguration(ConnectionFactory connectionFactory){
            this.connectionFactory = connectionFactory;
        }
         */


        // -------- Setando instâncias de configuração --------- //


        // Criando Bean Configuration do RabbitMQAdmin, para criação e gerência das filas por parte do Spring
        @Bean
        public RabbitAdmin criarRabbitAdmin(ConnectionFactory connectionFactory){
            return new RabbitAdmin(connectionFactory);
        }


        // Criando Bean para inicializar a criação de filas
        @Bean
        public ApplicationListener<ApplicationReadyEvent> inicializarAdmin(RabbitAdmin rabbitAdmin){
            return event->rabbitAdmin.initialize();
        }


        // -------- Setando Exchanges e Bindings --------- //


        //Criando a Exchange "proposta-pendente"
        //Setando @Bean para entrega do response do método à gerência do Spring.

        @Bean
        public FanoutExchange criarFanoutExchangePropostaPendente(){
            return ExchangeBuilder.fanoutExchange("proposta-pendente.ex").build();
        }

        //Setando o binds da Exchange "proposta-pendente"
        //Método seta Queues da Exchange específica
        //"PropostaPendenteMsAnaliseCredito"
        @Bean
        public Binding criarBindingPropostaPendenteMSAnaliseCredito(){
            return BindingBuilder.bind(criarFilaPropostaPendenteMsAnaliseCredito())
                                        .to(criarFanoutExchangePropostaPendente());
        }

        //"PropostaPendenteMsNotificacao"
        @Bean
        public Binding criarBindingPropostaPendenteMSNotificacao(){
            return BindingBuilder.bind(criarFilaPropostaPendenteMsNotificacao())
                    .to(criarFanoutExchangePropostaPendente());
        }






}
