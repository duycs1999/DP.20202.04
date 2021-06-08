package entity.shipping;

import org.example.AddressMapper;
import org.example.AlternativeDistanceCalculator;
import common.interfaces.DistanceCalculatorInterface;
import org.example.DistanceCalculator;

import java.util.Arrays;

public class AdapterDistanceCalculator implements DistanceCalculatorInterface {
    DistanceCalculator distanceCalculator = new DistanceCalculator();

    @Override
    public int calculateDistance(String province, String address) {
        return distanceCalculator.calculateDistance(address, province);
    }
}
