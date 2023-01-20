package com.casino.betting.config;


import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.casino.betting.entity.BettingDetail;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	@Bean
	public ConsumerFactory<String, BettingDetail> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroup");
		return new DefaultKafkaConsumerFactory<>(props,
			new StringDeserializer(), new JsonDeserializer<>(BettingDetail.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, BettingDetail>
	kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, BettingDetail> factory =
		new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}

