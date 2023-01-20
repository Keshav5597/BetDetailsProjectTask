
package com.casino.betting.kafkaservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.casino.betting.entity.BettingDetail;
import com.casino.betting.repository.BettingDetailsRepo;

@Service
public class BettingDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

	@Autowired
	private BettingDetailsRepo bettingDetailsRepo;
	@Autowired
	private KafkaTemplate<String, BettingDetail> kafkaTemplate;

	public List<BettingDetail> searchFilter(String game, Long clientId) {
		Specification<BettingDetail> specification = Specification.where(null);
		if (game != null) {
			specification = specification.and(hasGame(game));
		}
		if (clientId != null) {
			specification = specification.and(hasClientId(clientId));
		}
		return bettingDetailsRepo.findAll(specification);
	}

	private Specification<BettingDetail> hasGame(String game) {
		return (root, query, cb) -> cb.equal(root.get("game"), game);
	}

	private Specification<BettingDetail> hasClientId(Long clientId) {
		return (root, query, cb) -> cb.equal(root.get("clientId"), clientId);
	}

	public void sendNotification(BettingDetail bettingDetail) {
		kafkaTemplate.send("bet_details", bettingDetail);
	}
}
