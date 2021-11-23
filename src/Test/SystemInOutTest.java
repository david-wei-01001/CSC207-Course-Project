package Test;

import Java.SystemInOut;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class SystemInOutTest {
    SystemInOut inOut;
    @Rule
    public TestRule timeout = new DisableOnDebug(new Timeout(20));

    @BeforeEach
    void setUp() throws Exception {
        inOut = new SystemInOut();
    }


    @Test
    public static void main(String[] args) throws Exception {
        SystemInOut inOut = new SystemInOut();

//        inOut.getNonEmptyCredential("username");
        inOut.run();
        inOut.mainMenu();

    }

    @Test
    void getUsernameRegister() {
        inOut.getUsernameRegister();
    }

    @Test
    void getEmailRegister() {
    }

    @Test
    void getPasswordRegister() {
    }

    @Test
    void getNonEmptyCredential() {
        inOut.getNonEmptyCredential("username");
    }
}