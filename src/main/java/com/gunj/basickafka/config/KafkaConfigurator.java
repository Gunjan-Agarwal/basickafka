package com.gunj.basickafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import com.gunj.basickafka.model.Account;

@Configuration
public class KafkaConfigurator {
	
		@Bean
		public ProducerFactory<String, String> getStringFactory(){
			Map<String, Object> config = new HashMap<>();
			config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
			config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
			config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
			
			return new DefaultKafkaProducerFactory<>(config);
		}
		
		@Bean
		public KafkaTemplate<String, String> template(){
			return new KafkaTemplate<>(getStringFactory());
		}

		
		@Bean
		public ProducerFactory<String, Account> getFactory(){
			
			Map<String, Object> configMap = new HashMap<>();
			
			configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
			configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
			configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
			
			return new DefaultKafkaProducerFactory<>(configMap);
			
		}
		
		@Bean
		public KafkaTemplate<String, Account> accTemplate(){
			return new KafkaTemplate<>(getFactory());
		}
}
