package controller;

import common.exception.FailLoginException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author
 */
public class LoginTest {

    private AuthenticationController authenticationController;

    @BeforeEach
    void setup() throws Exception {
        authenticationController = new AuthenticationController();
    }

    @ParameterizedTest
    @CsvSource({
            "false, anv@mail.com, 123456789",
            "true, anv@mail.com, 123456"
    })
    void test(boolean expected, String email, String password) {
        boolean authenticated;
        try {
            authenticationController.login(email, password);
            authenticated = true;
        } catch (Exception ex) {
            authenticated = false;
            System.out.println(ex.getMessage());
        }
        assertEquals(expected, authenticated);
    }
}
