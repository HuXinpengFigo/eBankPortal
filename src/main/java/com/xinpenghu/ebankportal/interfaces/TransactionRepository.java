package com.xinpenghu.ebankportal.interfaces;

import com.xinpenghu.ebankportal.entity.Transaction;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findAllByEmail(String email);

    List<Transaction> findAllByDateBetween(LocalDate start, LocalDate end);
}
