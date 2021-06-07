package entity.shipping;
import common.interfaces.DistanceCalculatorInterface;

public class AdapterDistance implements DistanceCalculator{
    private DistanceCalculatorInterface distanceCalculatorInterface;
    public AdapterDistance(DistanceCalculatorInterface distanceCalculatorInterface){
        this.distanceCalculatorInterface = distanceCalculatorInterface;
    }

    @Override
    public int distanceCalculator(String address, String provide) {
        return this.distanceCalculatorInterface.distanceCalculator(address, provide);
    }
}
