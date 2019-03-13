//package com.revature.assignforce.messaging.config;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
//import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.converter.MappingJackson2MessageConverter;
//import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
//
//@Configuration
//public class RabbitConfig implements RabbitListenerConfigurer {
//
//	@Bean
//	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
//		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
//		factory.setMessageConverter(new MappingJackson2MessageConverter());
//		return factory;
//	}
//
//	@Override
//	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
//		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
//	}
//}
