package com.number26proj.test;

import com.number26proj.application.Application;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * This class is a test suite, it is used to run all tests at once
 * Created by NrapendraKumar on 06-03-2016.
 */

@RunWith(Suite.class)
@SpringApplicationConfiguration(Application.class)
@Suite.SuiteClasses({
        ApplicationDependencyInjectionTest.class,
        AppUtilTest.class,
        ComponentUtilTest.class,
        NumberUtilTest.class,
        TransactionControllerTest.class,
        TransactionServiceImplTest.class,
        URLUtilTest.class
})
public class TransactionAPITestSuite {
}
