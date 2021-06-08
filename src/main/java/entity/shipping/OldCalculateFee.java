package entity.shipping;

import entity.order.Order;

public class OldCalculateFee implements ICalculateStrategy{
    @Override
    public int calculateFee(Order order, int distance) {
        // tinh theo yeu cau cu chi dua tren khoang cach
        return distance;
    }
}
