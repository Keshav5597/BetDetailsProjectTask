
package com.casino.betting.kafkaservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.casino.betting.entity.BettingDetail;

@Service
public class MessageProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

	@Autowired
	private KafkaTemplate<String, BettingDetail> kafkaTemplate;

	public void sendMessage(BettingDetail bettingDetail) {
		LOGGER.info(String.format("Message is sent %s", bettingDetail));
		Message<BettingDetail> message = MessageBuilder.withPayload(bettingDetail)
				.setHeader(KafkaHeaders.TOPIC, "bet_details").build();
		kafkaTemplate.send(message);
	}

}
