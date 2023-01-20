package com.casino.betting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class BettingDetail {
	@Id
	private Long id;
	private Long numbets;
	private String game;
	private double stake;
	private double returns;
	private Long clientId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumbets() {
		return numbets;
	}

	public void setNumbets(Long numbets) {
		this.numbets = numbets;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public double getStake() {
		return stake;
	}

	public void setStake(double stake) {
		this.stake = stake;
	}

	public double getReturns() {
		return returns;
	}

	public void setReturns(double returns) {
		this.returns = returns;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "BettingDetail [id=" + id + ", numbets=" + numbets + ", game=" + game + ", stake=" + stake + ", returns="
				+ returns + ", clientId=" + clientId + "]";
	}

}
