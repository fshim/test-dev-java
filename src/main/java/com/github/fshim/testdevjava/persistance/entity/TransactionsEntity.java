package com.github.fshim.testdevjava.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "transactions")
public class TransactionsEntity {

    @Id
    @Getter
    private Long id;

    @Getter
    @Setter
    private BigDecimal amount;

}