package com.casino.betting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casino.betting.entity.BettingDetail;
import com.casino.betting.kafkaservice.BettingDetailsService;
import com.casino.betting.kafkaservice.KafkaMessageConsumer;
import com.casino.betting.kafkaservice.MessageProducer;

@RestController
@RequestMapping("/api/casino")
public class BettingDetailsController {

	@Autowired
	private MessageProducer messageProducer;
	@Autowired
	private BettingDetailsService bettingDetailsService;
	@Autowired
	private KafkaMessageConsumer kafkaMessageConsumer;

	@PostMapping("/saveBettingDetails")
	public ResponseEntity<?> saveBettingDetails(@RequestBody BettingDetail bettingDetail) {
		messageProducer.sendMessage(bettingDetail);
		return ResponseEntity.ok("Data is save in  database");
	}

	@PostMapping("/sendNotification")
	public void sendNotification(@RequestBody BettingDetail bettingDetail) {
		kafkaMessageConsumer.consume(bettingDetail);
	}

	@GetMapping("/getFilter")
	public List<BettingDetail> search(@RequestParam(value = "game", required = false) String game,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		return bettingDetailsService.searchFilter(game, clientId);
	}
}
