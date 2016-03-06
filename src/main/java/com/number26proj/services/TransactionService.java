package com.number26proj.services;

import com.number26proj.models.AmountSum;
import com.number26proj.models.Status;
import com.number26proj.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service interface has declared its features
 * Created by NrapendraKumar on 05-03-2016.
 */
@Service
public interface TransactionService {

    public Status addTransaction(Long transactionId, Transaction transaction);

    public Status deleteTransaction(Long transactionId);

    public AmountSum getTransactionSum(Long transactionId);

    public Transaction getTransaction(Long transactionId);

    public List<Long> getTransactionIdsByType(String type);
}