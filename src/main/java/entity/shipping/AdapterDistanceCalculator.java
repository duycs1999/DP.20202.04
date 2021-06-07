package entity.shipping;

import org.example.AlternativeDistanceCalculator;

public class AdapterDistanceCalculator extends DeliveryInfo{
    private AlternativeDistanceCalculator alternativeDistanceCalculator;

    public AdapterDistanceCalculator(String name, String phone, String province, String address, String shippingInstructions, AlternativeDistanceCalculator alternativeDistanceCalculator) {
        super(name, phone, province, address, shippingInstructions, null);
        this.alternativeDistanceCalculator = alternativeDistanceCalculator;
    }
}
