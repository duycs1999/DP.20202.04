package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatePhoneNumberTest {

    private PlaceOrderController placeOrderController;

    @BeforeEach
    void setup() throws Exception {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
            "true, 0123456789",
            "false, 01234",
            "false, a1234567890",
            "false, 1234567890"
    })
    void test(boolean expected, String phoneNumber) {
        boolean isValid = placeOrderController.validatePhoneNumber(phoneNumber);
        assertEquals(expected, isValid);
    }
}
