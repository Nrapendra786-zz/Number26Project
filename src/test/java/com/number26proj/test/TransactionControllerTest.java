package com.number26proj.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.number26proj.application.Application;
import com.number26proj.controllers.TransactionController;
import com.number26proj.exceptions.NotFoundException;
import com.number26proj.exceptions.PreConditionFailedException;
import com.number26proj.models.AmountSum;
import com.number26proj.models.Status;
import com.number26proj.models.Transaction;
import com.number26proj.services.TransactionService;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * This class is used to test Transaction Controller class
 * Created by NrapendraKumar on 06-03-2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class TransactionControllerTest {

    @Autowired
    private TransactionController transactionController;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testPassAddTransaction() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setAmount(2200.00d);
        transaction.setParent_id(103);
        transaction.setType("cars");
        ResponseEntity<Status> status = transactionController.addTransaction("7", transaction);
        Assert.assertEquals(status.getStatusCode(), OK);
    }

    @Test
    public void testNotFoundException() throws Exception {
        exception.expect(NotFoundException.class);
        exception.expectMessage("Transaction Id not found");
        transactionController.getTransaction("102l");
    }

    @Test
    public void testGetTransactionSum() {
        Transaction transactionFirst = new Transaction();
        transactionFirst.setAmount(2200.00d);
        transactionFirst.setParent_id(10l);
        transactionFirst.setType("cars");
        Transaction transactionSecond = new Transaction();
        transactionSecond.setAmount(4400.00d);
        transactionSecond.setParent_id(10l);
        transactionSecond.setType("cars");
        transactionController.addTransaction("9", transactionFirst);
        transactionController.addTransaction("10", transactionSecond);
        ResponseEntity<AmountSum> response = transactionController.getTransactionSum("10");
        AmountSum sum = response.getBody();
        Assert.assertEquals(new Double(sum.getSum()), new Double(6600.00d));
    }

    @Test
    public void testGetTypes() {
        Transaction transactionFirst = new Transaction();
        transactionFirst.setAmount(2200.00d);
        transactionFirst.setParent_id(102);
        transactionFirst.setType("cars");
        Transaction transactionSecond = new Transaction();
        transactionSecond.setAmount(3300.00d);
        transactionSecond.setParent_id(102);
        transactionSecond.setType("cars");
        Transaction transactionThird = new Transaction();
        transactionThird.setAmount(4400.00d);
        transactionThird.setParent_id(102);
        transactionThird.setType("shopping");
        Transaction transactionFourth = new Transaction();
        transactionFourth.setAmount(5500.00d);
        transactionFourth.setParent_id(102);
        transactionFourth.setType("shopping");
        transactionController.addTransaction("11", transactionFirst);
        transactionController.addTransaction("12", transactionSecond);
        transactionController.addTransaction("13", transactionThird);
        transactionController.addTransaction("14", transactionFourth);
        ResponseEntity<List<Long>> responseFirst = transactionController.getType("shopping");
        ResponseEntity<List<Long>> responseSecond = transactionController.getType("cars");
        Assert.assertEquals(responseFirst.getBody().size(), 2);
        Assert.assertEquals(new Long(responseFirst.getBody().get(0)), new Long(13l));
        Assert.assertEquals(new Long(responseFirst.getBody().get(1)), new Long(14l));
        Assert.assertEquals(responseFirst.getBody().size(), 2);
        Assert.assertEquals(new Long(responseSecond.getBody().get(0)), new Long(11l));
        Assert.assertEquals(new Long(responseSecond.getBody().get(1)), new Long(12l));
    }

    @Test
    public void testPassRemoveTransaction() {
        Transaction transactionFirst = new Transaction();
        transactionFirst.setAmount(2200.00d);
        transactionFirst.setParent_id(103);
        transactionFirst.setType("cars");
        transactionController.addTransaction("15", transactionFirst);
        ResponseEntity<Status> responseFirst = transactionController.removeTransaction("15");
        Assert.assertEquals(responseFirst.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testFailRemoveTransaction() {
        exception.expect(NotFoundException.class);
        exception.expectMessage("Transaction Id not found");
        Transaction transactionFirst = new Transaction();
        transactionFirst.setAmount(2200.00d);
        transactionFirst.setParent_id(103);
        transactionFirst.setType("cars");
        transactionController.addTransaction("15", transactionFirst);
        ResponseEntity<Status> responseFirst = transactionController.removeTransaction("16");
        Assert.assertEquals(responseFirst.getStatusCode(), HttpStatus.OK);
    }
}
