/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.amqp.tutorials.tut8;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Gary Russell
 * @author Scott Deeg
 * @author Eloi Mendes
 *
 */
@Profile({"tut8","reply2"})
@Configuration
public class Tut8Config {


	@Bean
	public DirectExchange exchange() {
		return new DirectExchange("tut.rep");
	}

	@Bean
	public Queue queue() {
		return new Queue("tut.rep.requests");
	}

	@Bean
	public Binding binding(DirectExchange exchange, Queue queue) {
		return BindingBuilder.bind(queue).to(exchange).with("request");
	}

	@Bean
	public Tut8Server server() {
		return new Tut8Server();
	}

	@Bean(name="rabbitListenerCommonContainerFactory")
	public SimpleRabbitListenerContainerFactory rabbitListenerCommonContainerFactory() {
		final ConnectionFactory commonConnectionFactory = getLogConnectionFactory("common", "secret", "localhost:5672", "common");

		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(commonConnectionFactory);

		return factory;
	}

	private ConnectionFactory getLogConnectionFactory(String user, String pass, String addresses, String virtualHost){
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setUsername(user);
		connectionFactory.setAddresses(addresses);
		connectionFactory.setPassword(pass);
		connectionFactory.setVirtualHost(virtualHost);
//			connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
		return connectionFactory;
	}

}
