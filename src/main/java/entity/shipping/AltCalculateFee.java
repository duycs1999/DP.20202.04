package entity.shipping;

import entity.order.Order;

public class AltCalculateFee implements ICalculateStrategy{
    @Override
    public int calculateFee(Order order, int distance) {
        // tinh theo yeu cau moi
        // dua tren khoi luong, do cong kenh cua don hang va khoang cach
        return 0; // gia tri nay chi la mo phong vi khong co khoi luong hay do cong kenh chinh xac cua don hang
    }
}
