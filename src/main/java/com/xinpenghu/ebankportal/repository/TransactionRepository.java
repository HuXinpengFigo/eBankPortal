package com.xinpenghu.ebankportal.repository;

import com.xinpenghu.ebankportal.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findAllByEmail(String email);

    List<Transaction> findAllByDateBetweenAndEmail(LocalDate start, LocalDate end, String email);

    List<Transaction> findAllByEmailAndType(String email, String type);

    List<Transaction> findAllByEmailAndCurrency(String email, String currency);
}
