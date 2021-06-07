
import org.example.AlternativeDistanceCalculator;
import org.example.DistanceCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author
 */
public class CalculateDistanceTest {

    private DistanceCalculator distanceCalculator;
    private AlternativeDistanceCalculator altDistanceCalculator;

    @BeforeEach
    void setup() throws Exception {
        distanceCalculator = new DistanceCalculator();
        altDistanceCalculator = new AlternativeDistanceCalculator();
    }

    @ParameterizedTest
    @CsvSource({
            "true, anv@mail.com, Hà Nội",
            "false, anv@mail.com, New York"
    })
    void test(boolean expected, String address, String province) {
        boolean result;
        try {
            distanceCalculator.calculateDistance(address, province);
            altDistanceCalculator.calculateDistance(province, address);
            result = true;
        } catch (Exception ex) {
            result = false;
            System.out.println(ex.getMessage());
        }
        assertEquals(expected, result);
    }
}
