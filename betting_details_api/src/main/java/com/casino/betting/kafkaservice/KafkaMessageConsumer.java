package com.casino.betting.kafkaservice;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.casino.betting.entity.BettingDetail;
import com.casino.betting.repository.BettingDetailsRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaMessageConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

	@Autowired
	private BettingDetailsRepo bettingDetailsRepo;

	@KafkaListener(topics = "bet_details", groupId = "myGroup")
	public void consume(BettingDetail bettingDetail) {
		bettingDetailsRepo.save(bettingDetail);
		LOGGER.info(String.format("Message is recevied -> %s", bettingDetail.toString()));
	}

	@KafkaListener(topics = "bet_details", groupId = "myGroup")
	public void sendNotification(BettingDetail bettingDetail) {
		LOGGER.info("Consumed message ->{}", bettingDetail);
		if (bettingDetail.getReturns() >= 1500.00) {
			LOGGER.info("Notification:High returns of {} for bettingDetails {}", bettingDetail.getReturns(),
					bettingDetail.getId());
		}
	}
}
