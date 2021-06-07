package entity.shipping;
import org.example.DistanceCalculator;
import common.interfaces.DistanceCalculatorInterface;

public class AdapterNewDistance implements DistanceCalculatorInterface{
    @Override
    public int distanceCalculator(String address, String province) {
        DistanceCalculator distanceCalculator= new DistanceCalculator();
        return distanceCalculator.calculateDistance(address, province);
    }
}
