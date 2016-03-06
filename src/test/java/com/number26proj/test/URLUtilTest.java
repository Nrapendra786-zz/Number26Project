package com.number26proj.test;

import com.number26proj.application.Application;
import com.number26proj.utils.ComponentUtil;
import com.number26proj.utils.URLUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This class is used to test URLUtil class
 * Created by NrapendraKumar on 06-03-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class URLUtilTest {

    @Test
    public void testPassComponentUtil() {
        Assert.assertEquals(URLUtil.TRANSACTION_URL_VALUE, "/transactionservice/transaction/{transaction_id}");
        Assert.assertEquals(URLUtil.TRANSACTION_SUM_URL_VALUE, "/transactionservice/sum/{transaction_id}");
        Assert.assertEquals(URLUtil.TRANSACTION_TYPE_URL_VALUE, "/transactionservice/types/{type}");
    }

    @Test
    public void testFailComponentUtil() {
        Assert.assertNotEquals(URLUtil.TRANSACTION_URL_VALUE, "com.number26proj.controllers");
        Assert.assertNotEquals(URLUtil.TRANSACTION_SUM_URL_VALUE, "com.number26proj.resources");
        Assert.assertNotEquals(URLUtil.TRANSACTION_TYPE_URL_VALUE, "com.number26proj.services");
    }
}
