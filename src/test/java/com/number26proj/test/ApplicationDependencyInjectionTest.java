package com.number26proj.test;

import com.number26proj.application.Application;
import com.number26proj.controllers.TransactionController;
import com.number26proj.resources.TransactionResource;
import com.number26proj.services.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * This class is used to test Dependency Injection of Components in an Application
 * Created by NrapendraKumar on 06-03-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationDependencyInjectionTest {

    @Autowired
    private TransactionController transactionController;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionResource transactionResource;

    private TransactionController transactionControllerObj;

    private TransactionService transactionServiceObj;

    private TransactionResource transactionResourceObj;

    @Test
    public void testPassTransactionControllerDITest() {
        Assert.notNull(transactionController);
    }

    @Test
    public void testPassTransactionServiceDITest() {
        Assert.notNull(transactionService);
    }

    @Test
    public void testPassTransactionResourceTest() {
        Assert.notNull(transactionResource);
    }

    @Test
    public void testFailTransactionControllerDITest() {
        Assert.isNull(transactionControllerObj);
    }

    @Test
    public void testFailTransactionServiceDITest() {
        Assert.isNull(transactionServiceObj);
    }

    @Test
    public void testFailTransactionResourceTest() {
        Assert.isNull(transactionResourceObj);
    }

}
