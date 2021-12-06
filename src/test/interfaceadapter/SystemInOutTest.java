package interfaceadapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SystemInOutTest {
    SystemInOut inOut;

    @BeforeEach
    void setUp() throws Exception {
        inOut = new SystemInOut();
    }


    @Test
    public static void main(String[] args) throws Exception {
        SystemInOut inOut = new SystemInOut();
        inOut.run();
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
}