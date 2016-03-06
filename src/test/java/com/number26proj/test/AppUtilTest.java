package com.number26proj.test;

import com.number26proj.application.Application;
import com.number26proj.utils.AppUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This class is used to test AppUtil
 * Created by NrapendraKumar on 06-03-2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class AppUtilTest {

    @Test
    public void testPassAppUtil() {
        Assert.assertEquals(AppUtil.OK, "ok");
        Assert.assertEquals(AppUtil.FAILED, "failed");
        Assert.assertEquals(AppUtil.TRANSACTION_ID, "transaction_id");
        Assert.assertEquals(AppUtil.TYPE, "type");
    }

    @Test
    public void testFailAppUtil() {
        Assert.assertNotEquals(AppUtil.OK, "ok1");
        Assert.assertNotEquals(AppUtil.FAILED, "pass");
        Assert.assertNotEquals(AppUtil.TRANSACTION_ID, "notransaction");
        Assert.assertNotEquals(AppUtil.TYPE, "notype");
    }
}
