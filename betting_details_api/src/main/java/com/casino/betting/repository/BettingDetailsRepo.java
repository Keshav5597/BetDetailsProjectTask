package com.casino.betting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.casino.betting.entity.BettingDetail;

public interface BettingDetailsRepo
		extends JpaRepository<BettingDetail, Long>, JpaSpecificationExecutor<BettingDetail> {

}
