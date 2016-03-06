package com.number26proj.test;

import com.number26proj.utils.ComponentUtil;
import com.number26proj.utils.NumberUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class is used to test NumberUtil class
 * Created by NrapendraKumar on 06-03-2016.
 */
public class NumberUtilTest {
    @Test
    public void testPassComponentUtil() {
        Assert.assertEquals(NumberUtil.ZERO, new Double(0d));
        Assert.assertEquals(NumberUtil.TWO, new Integer(2));
    }

    @Test
    public void testFailComponentUtil() {
        Assert.assertNotEquals(NumberUtil.ZERO, new Integer(1));
        Assert.assertNotEquals(NumberUtil.TWO, new Double(0d));
    }
}
