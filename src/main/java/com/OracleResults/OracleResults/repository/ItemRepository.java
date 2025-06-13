package com.OracleResults.OracleResults.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.OracleResults.OracleResults.dto.ItemOracle;

public interface ItemRepository extends JpaRepository<ItemOracle, Long>, JpaSpecificationExecutor<ItemOracle> {

    boolean existsByJob(String job);
}
