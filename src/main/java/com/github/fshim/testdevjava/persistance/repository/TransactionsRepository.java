package com.github.fshim.testdevjava.persistance.repository;

import com.github.fshim.testdevjava.persistance.entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Long> {
}
