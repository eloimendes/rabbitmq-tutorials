package org.springframework.amqp.tutorials.tut8;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Component
//@RequiredArgsConstructor
public class RabbitMQConsumerConfig {

//    private final ConnectionFactory connectionFactory;

    @Bean
    public RabbitAdmin rabbitAdmin() {
        final ConnectionFactory logConnectionFactory = getLogConnectionFactory("common", "secret", "localhost:5672", "common");
        return new RabbitAdmin(logConnectionFactory);
    }

    public ConnectionFactory getLogConnectionFactory(String user, String pass, String addresses, String virtualHost){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername(user);
        connectionFactory.setAddresses(addresses);
        connectionFactory.setPassword(pass);
        connectionFactory.setVirtualHost(virtualHost);
//        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
        return connectionFactory;
    }

}