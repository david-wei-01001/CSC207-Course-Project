package phase1;

import jdk.jshell.spi.SPIResolutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PresenterTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getNonEmptyCredential() {

    }

    @Test
    public static void main(String[] args) {
        Presenter presenter = new Presenter();
        presenter.getNonEmptyCredential(Presenter.USERNAME);
    }


}