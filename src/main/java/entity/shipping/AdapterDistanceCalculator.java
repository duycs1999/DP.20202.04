package entity.shipping;

import common.interfaces.DistanceCalculatorInterface;
import org.example.DistanceCalculator;

public class AdapterDistanceCalculator implements DistanceCalculatorInterface {
    DistanceCalculator distanceCalculator = new DistanceCalculator();

    @Override
    public int calculateDistance(String province, String address) {
        return distanceCalculator.calculateDistance(address, province);
    }
}
