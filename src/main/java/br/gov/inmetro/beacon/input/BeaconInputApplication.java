package br.gov.inmetro.beacon.input;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BeaconInputApplication {

//	public static final String topicExchangeName = "spring-boot-exchange";
////
//	static final String queueName = "spring-boot";
//

//	@Bean
//	Queue queue() {
//		return new Queue(queueName, true);
//	}
////
//	@Bean
//	TopicExchange exchange() {
//		return new TopicExchange(topicExchangeName);
//	}
////
//	@Bean
//	Binding binding(Queue queue, TopicExchange exchange) {
//		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
//	}
//
//	@Bean
//	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
//		container.setQueueNames(queueName);
////		container.setMessageListener(listenerAdapter);
//		return container;
//	}


//	@Value("${queue.order.name}")
//	private String orderQueue;
//
//	@Bean
//	public Queue queue() {
//		return new Queue(orderQueue, true);
//	}


	public static void main(String[] args) {
		SpringApplication.run(BeaconInputApplication.class, args);
	}

}

