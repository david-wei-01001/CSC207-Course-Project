package SystemInOut;

import interfaceadapter.SystemInOut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for SystemInOut class
 */
class SystemInOutTest {

    @Test
    public void test() throws Exception {
        String[] registers = {"2", "david", "david@123.com", "123456", "return", "exit", "exit"};
        TestingInput uiAdapter1 = new TestingInput(registers);
        SystemInOut inOut1 = new SystemInOut(uiAdapter1);
        inOut1.run();
        String[] inputs = {"1", "david", "123456", "1", "2", "0", "yes", "good", "1", "2",
                "yes", "good", "1", "main", "0", "main", "2", "3", "good", "5", "good", "1", "2", "2",
                "Resource #0", "1", "2", "1", "1", "3", "1", "exit"};
        TestingInput uiAdapter2 = new TestingInput(inputs);
        SystemInOut inOut2 = new SystemInOut(uiAdapter2);
        inOut2.run();
        Assertions.assertTrue(uiAdapter2.getToEnd());
    }




















}