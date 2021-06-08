package entity.shipping;

import entity.order.Order;
import org.example.DistanceCalculator;


public class DeliveryInfo {
    private static final float DISTANCE_FACTOR = 1.2f;
    protected String name;
    protected String phone;
    protected String province;
    protected String address;
    protected String shippingInstructions;
    protected DistanceCalculator distanceCalculator;
    private ICalculateStrategy strategy;
    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculator distanceCalculator) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.shippingInstructions = shippingInstructions;
        this.distanceCalculator = distanceCalculator;
        // Mac dinh ban dau van se su dung theo phuong thuc cu, Muon thay doi chi can setCalculateStrategy
        setCalculateStrategy(new OldCalculateFee());
    }
    public void setCalculateStrategy(ICalculateStrategy strategy) {
        this.strategy = strategy;
    }

//vi pham nguyen ly OCP
// khi thay doi cach tinh phi ship thi phai thay doi 
 // ngoai ra con phu thuoc vao DistanceCalculator, khi thay doi cach tinh bang thu vien moi thi phai thay doi
    public int calculateShippingFee(Order order) {
        int distance = distanceCalculator.calculateDistance(address, province);
        int cost = this.strategy.calculateFee(order,distance);
        return (int) (cost * DISTANCE_FACTOR);
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getProvince() {
        return province;
    }

    public String getAddress() {
        return address;
    }

    public String getShippingInstructions() {
        return shippingInstructions;
    }
}
