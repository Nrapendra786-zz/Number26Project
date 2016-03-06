package com.number26proj.test;

import com.number26proj.application.Application;
import com.number26proj.utils.AppUtil;
import com.number26proj.utils.ComponentUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This class is used to test ComponentUtil class
 * Created by NrapendraKumar on 06-03-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ComponentUtilTest {

    @Test
    public void testPassComponentUtil() {
        Assert.assertEquals(ComponentUtil.CONTROLLERS, "com.number26proj.controllers");
        Assert.assertEquals(ComponentUtil.RESOURCES, "com.number26proj.resources");
        Assert.assertEquals(ComponentUtil.SERVICES, "com.number26proj.services");
        Assert.assertEquals(ComponentUtil.MODELS, "com.number26proj.models");
    }

    @Test
    public void testFailComponentUtil() {
        Assert.assertNotEquals(ComponentUtil.CONTROLLERS, "com.number26proj.controllers1");
        Assert.assertNotEquals(ComponentUtil.RESOURCES, "com.number26proj.models");
        Assert.assertNotEquals(ComponentUtil.SERVICES, "com.number26proj.resources");
        Assert.assertNotEquals(ComponentUtil.MODELS, "com.number26proj.services");
    }
}
