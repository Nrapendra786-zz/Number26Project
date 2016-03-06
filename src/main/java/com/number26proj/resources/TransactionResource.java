package com.number26proj.resources;

import com.number26proj.controllers.TransactionController;
import com.number26proj.models.Status;
import com.number26proj.models.Transaction;
import com.number26proj.utils.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * This class act as a resource for Transaction and can be replaced with DataBase
 * Created by NrapendraKumar on 05-03-2016.
 */

@Component
public class TransactionResource {

    private static Map<Long, Transaction> transactionStore = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class.getName());

    public void storeTransaction(Long transactionId, Transaction transaction) {
        synchronized (this) {
            transactionStore.put(transactionId, transaction);
        }
    }

    public Transaction getTransaction(Long transactionId) {
        Transaction transaction = getTransactionStore().get(transactionId);
        return transaction;
    }

    public void deleteTransaction(Long transactionId) {
        synchronized (this) {
            transactionStore.remove(transactionId);
        }
    }

    public Map<Long, Transaction> getTransactionStore() {
        return transactionStore;
    }
}
