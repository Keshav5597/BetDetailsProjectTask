package com.casino.betting.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	
	public NewTopic bettingPrNewTopic() {
		return TopicBuilder.name("bet_details")
				.build();
	}
}
