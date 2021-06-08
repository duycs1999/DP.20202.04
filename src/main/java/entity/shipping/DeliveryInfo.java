package entity.shipping;

import common.interfaces.DistanceCalculatorInterface;
import entity.order.Order;
import org.example.DistanceCalculator;


public class DeliveryInfo {
    private static final float DISTANCE_FACTOR = 1.2f;
    protected String name;
    protected String phone;
    protected String province;
    protected String address;
    protected String shippingInstructions;
    protected DistanceCalculatorInterface distanceCalculatorInterface;

    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculatorInterface distanceCalculatorInterface) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.shippingInstructions = shippingInstructions;
        this.distanceCalculatorInterface = distanceCalculatorInterface;
    }

//vi pham nguyen ly OCP
// khi thay doi cach tinh phi ship thi phai thay doi 
 // ngoai ra con phu thuoc vao DistanceCalculator, khi thay doi cach tinh bang thu vien moi thi phai thay doi
    public int calculateShippingFee(Order order) {
        int distance = distanceCalculatorInterface.calculateDistance(province,address);
        return (int) (distance * DISTANCE_FACTOR);
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
