package entity.shipping;

import common.interfaces.DistanceCalculatorInterface;
import org.example.AlternativeDistanceCalculator;

public class AdapterAlternativeDistanceCalculator implements DistanceCalculatorInterface {
    AlternativeDistanceCalculator alternativeDistanceCalculator = new AlternativeDistanceCalculator();
    @Override
    public int calculateDistance(String province, String address) {
        return alternativeDistanceCalculator.calculateDistance(province,address);
    }
}
