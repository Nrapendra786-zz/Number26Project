package com.number26proj.services;

import com.number26proj.enums.ErrorRepository;
import com.number26proj.exceptions.NotFoundException;
import com.number26proj.exceptions.PreConditionFailedException;
import com.number26proj.models.AmountSum;
import com.number26proj.models.Status;
import com.number26proj.models.Transaction;
import com.number26proj.resources.TransactionResource;
import com.number26proj.services.TransactionService;
import com.number26proj.utils.AppUtil;
import com.number26proj.utils.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * This service class has implemented features declared in Transaction Service
 * Created by NrapendraKumar on 05-03-2016.
 */

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionResource transactionResource;

    @Autowired
    private AmountSum amountSum;

    @Autowired
    private Status transactionStatus;

    @Override
    public Status addTransaction(Long transactionId, Transaction transaction) {
        if (transactionResource.getTransaction(transactionId) != null) {
            throw new PreConditionFailedException(ErrorRepository.TRANSACTION_ALREADY_EXIST);
        } else {
            transactionResource.storeTransaction(transactionId, transaction);
            transactionStatus.setStatus(AppUtil.OK);
        }
        return transactionStatus;
    }

    @Override
    public Status deleteTransaction(Long transactionId) {
        if (getTransaction(transactionId) != null) {
            transactionResource.deleteTransaction(transactionId);
            transactionStatus.setStatus(AppUtil.OK);
        }
        return transactionStatus;
    }

    @Override
    public AmountSum getTransactionSum(Long transactionId) {
        List<Transaction> transactionList = getTransactions();
        Transaction transaction = getTransaction(transactionId);
        Long parentId = transaction.getParent_id();
        double sum = NumberUtil.ZERO;
        for (Transaction transactionObj : transactionList) {
            if (parentId.equals(transactionObj.getParent_id())) {
                sum += transactionObj.getAmount();
            }
        }
        amountSum.setSum(roundTwo(sum));
        return amountSum;
    }

    @Override
    public Transaction getTransaction(Long transactionId) {
        Transaction transaction = transactionResource.getTransaction(transactionId);
        if (transaction == null) {
            throw new NotFoundException(ErrorRepository.TRANSACTION_NOT_FOUND);
        }
        return transaction;
    }

    @Override
    public List<Long> getTransactionIdsByType(String type) {
        List<Transaction> transactionList = getTransactions();
        List<Long> transactionIds = new ArrayList<>(transactionList.size());
        for (Transaction transaction : transactionList) {
            if (type.equals(transaction.getType())) {
                Set<Map.Entry<Long, Transaction>> entries = transactionResource.getTransactionStore().entrySet();
                for (Map.Entry<Long, Transaction> entry : entries) {
                    if (entry.getValue().equals(transaction)) {
                        transactionIds.add(entry.getKey());
                    }
                }
            }
        }
        return transactionIds;
    }

    private List<Transaction> getTransactions() {
        return new ArrayList<>(transactionResource.getTransactionStore().values());
    }

    private Double roundTwo(Double value) {
        return new BigDecimal(value.toString()).setScale(NumberUtil.TWO, RoundingMode.HALF_EVEN).doubleValue();
    }
}