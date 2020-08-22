package runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tests.CreateCustomOderWithIngredientsTest;
import tests.OrderItemFromMenuTheTest;
import tests.SendOrderToEmailTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
    {CreateCustomOderWithIngredientsTest.class,
        OrderItemFromMenuTheTest.class,
        SendOrderToEmailTest.class
    })
public class InstrumentationTestSuite {}
