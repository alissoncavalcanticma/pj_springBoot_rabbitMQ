package com.ctfera.java_spb.config;

//import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {


        //Intanciando Exchange e passado valor de property
        @Value("${spring.rabbitmq.propostapendente.exchange}")
        private String exchange;

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
            return ExchangeBuilder.fanoutExchange(exchange).build();
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





        //Criado um Bean MessageConverter com o Jackson para converter Message do Rabbit em JSON e usar no @Bean de RabbitTemplate criado
        @Bean
        public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
            return new Jackson2JsonMessageConverter();
        }

        //Criando Bean de RabbitTemplate
        // Definindo ConectionFactory
        // Definindo MessageConverter
        @Bean
        public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
            RabbitTemplate rabbitTemplate = new RabbitTemplate();
            rabbitTemplate.setConnectionFactory(connectionFactory);
            rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());

            return rabbitTemplate;
        }

}
