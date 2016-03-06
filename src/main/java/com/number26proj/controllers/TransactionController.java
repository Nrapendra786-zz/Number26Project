package com.number26proj.controllers;

import com.number26proj.services.TransactionService;
import com.number26proj.models.AmountSum;
import com.number26proj.models.Status;
import com.number26proj.models.Transaction;
import com.number26proj.utils.AppUtil;
import com.number26proj.utils.URLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a Controller class and responsible for mapping request
 * Created by NrapendraKumar on 05-03-2016.
 */

@Controller
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class.getName());

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = URLUtil.TRANSACTION_URL_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<Status> addTransaction(@PathVariable(AppUtil.TRANSACTION_ID) String transactionId, @RequestBody Transaction transaction) {
        Long transaction_Id = parseTransactionId(transactionId);
        Status status = transactionService.addTransaction(transaction_Id, transaction);
        return new ResponseEntity<Status>(status, HttpStatus.OK);
    }

    @RequestMapping(value = URLUtil.TRANSACTION_URL_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Transaction> getTransaction(@PathVariable(AppUtil.TRANSACTION_ID) String transactionId) {
        Long transaction_Id = parseTransactionId(transactionId);
        Transaction transaction = transactionService.getTransaction(transaction_Id);
        return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
    }

    @RequestMapping(value = URLUtil.TRANSACTION_URL_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity<Status> removeTransaction(@PathVariable(AppUtil.TRANSACTION_ID) String transactionId) {
        Long transaction_Id = parseTransactionId(transactionId);
        Status status = transactionService.deleteTransaction(transaction_Id);
        return new ResponseEntity<Status>(status, HttpStatus.OK);
    }

    @RequestMapping(value = URLUtil.TRANSACTION_SUM_URL_VALUE, method = RequestMethod.GET)
    public ResponseEntity<AmountSum> getTransactionSum(@PathVariable(AppUtil.TRANSACTION_ID) String transactionId) {
        Long transaction_Id = parseTransactionId(transactionId);
        AmountSum amountSum = transactionService.getTransactionSum(transaction_Id);
        return new ResponseEntity<AmountSum>(amountSum, HttpStatus.OK);
    }

    @RequestMapping(value = URLUtil.TRANSACTION_TYPE_URL_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<Long>> getType(@PathVariable(AppUtil.TYPE) String type) {
        List<Long> types = transactionService.getTransactionIdsByType(type);
        return new ResponseEntity<List<Long>>(types, HttpStatus.OK);
    }

    private Long parseTransactionId(String transactionId) {
        Long transaction_Id = 0l;
        try {
            transaction_Id = Long.parseLong(transactionId);
        } catch (Exception ex) {
            // throw transaction Id Parse Exception
        }
        return transaction_Id;
    }
}

