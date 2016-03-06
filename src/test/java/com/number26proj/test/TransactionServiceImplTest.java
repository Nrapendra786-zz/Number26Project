package com.number26proj.test;

import com.number26proj.application.Application;
import com.number26proj.exceptions.NotFoundException;
import com.number26proj.exceptions.PreConditionFailedException;
import com.number26proj.models.AmountSum;
import com.number26proj.models.Status;
import com.number26proj.models.Transaction;
import com.number26proj.services.TransactionService;
import com.number26proj.services.TransactionServiceImpl;
import org.junit.BeforeClass;
import org.junit.*;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * This class is used to test TransactionServiceImpl class
 * Created by NrapendraKumar on 06-03-2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class TransactionServiceImplTest {

    private static TransactionServiceImpl transactionServiceImpl;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private TransactionService transactionService;

    @BeforeClass
    public static void beforeClass() {
        transactionServiceImpl = new TransactionServiceImpl();
    }

    @Test
    public void testTransactionSum() {
        Transaction transactionFirst = new Transaction();
        transactionFirst.setAmount(2200.00d);
        transactionFirst.setParent_id(105);
        transactionFirst.setType("cars");
        Transaction transactionSecond = new Transaction();
        transactionSecond.setAmount(4400.00d);
        transactionSecond.setParent_id(105);
        transactionSecond.setType("cars");
        transactionService.addTransaction(20l, transactionFirst);
        transactionService.addTransaction(21l, transactionSecond);
        AmountSum amountSum = transactionService.getTransactionSum(20l);
        Double expectedValue = new Double(6600.00d);
        Assert.assertEquals(expectedValue, new Double(amountSum.getSum()));
    }

    @Test
    public void testTransactionType() {
        Transaction transactionFirst = new Transaction();
        transactionFirst.setAmount(2200.00d);
        transactionFirst.setParent_id(106);
        transactionFirst.setType("scooter");
        Transaction transactionSecond = new Transaction();
        transactionSecond.setAmount(4400.00d);
        transactionSecond.setParent_id(106);
        transactionSecond.setType("scooter");
        Transaction transactionThird = new Transaction();
        transactionThird.setAmount(2200.00d);
        transactionThird.setParent_id(106);
        transactionThird.setType("restaurant");
        Transaction transactionFourth = new Transaction();
        transactionFourth.setAmount(4400.00d);
        transactionFourth.setParent_id(106);
        transactionFourth.setType("restaurant");
        transactionService.addTransaction(22l, transactionFirst);
        transactionService.addTransaction(23l, transactionSecond);
        transactionService.addTransaction(24l, transactionThird);
        transactionService.addTransaction(25l, transactionFourth);
        List<Long> transactionOneIds = transactionService.getTransactionIdsByType("scooter");
        List<Long> transactionTwoIds = transactionService.getTransactionIdsByType("restaurant");
        Assert.assertEquals(new Long(22l), new Long(transactionOneIds.get(0)));
        Assert.assertEquals(new Long(23l), new Long(transactionOneIds.get(1)));
        Assert.assertEquals(new Long(24l), new Long(transactionTwoIds.get(0)));
        Assert.assertEquals(new Long(25l), new Long(transactionTwoIds.get(1)));
    }

    @Test
    public void testDeletePassTransaction() {
        Transaction transactionFirst = new Transaction();
        transactionFirst.setAmount(2200.00d);
        transactionFirst.setParent_id(106);
        transactionFirst.setType("scooter");
        transactionService.addTransaction(26l, transactionFirst);
        Status status = transactionService.deleteTransaction(26l);
        Assert.assertEquals(status.getStatus(), "ok");
    }

    @Test
    public void testDeleteFailTransaction() {
        exception.expect(NotFoundException.class);
        Transaction transactionFirst = new Transaction();
        transactionFirst.setAmount(2200.00d);
        transactionFirst.setParent_id(107);
        transactionFirst.setType("scooter");
        transactionService.addTransaction(27l, transactionFirst);
        transactionService.deleteTransaction(28l);
    }

    @Test
    public void testAddPassTransaction() {
        Transaction transactionFirst = new Transaction();
        transactionFirst.setAmount(2200.00d);
        transactionFirst.setParent_id(107);
        transactionFirst.setType("books");
        Status status = transactionService.addTransaction(29l, transactionFirst);
        Assert.assertEquals(status.getStatus(), "ok");
    }

    @Test
    public void testAddFailTransaction() {
        exception.expect(PreConditionFailedException.class);
        Transaction transactionFirst = new Transaction();
        transactionFirst.setAmount(2200.00d);
        transactionFirst.setParent_id(107);
        transactionFirst.setType("books");
        transactionService.addTransaction(30l, transactionFirst);
        transactionService.addTransaction(30l, transactionFirst);
    }

    @Test
    public void testRoundTwo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        double value = 2.309887777d;
        Double expectedValue = new Double(2.31d);
        Method method = TransactionServiceImpl.class.getDeclaredMethod("roundTwo", Double.class);
        method.setAccessible(true);
        Double responseValue = (Double) method.invoke(transactionServiceImpl, value);
        Assert.assertEquals(expectedValue, responseValue);
    }


}
