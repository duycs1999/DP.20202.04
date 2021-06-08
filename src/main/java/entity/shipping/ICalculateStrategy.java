package entity.shipping;

import entity.order.Order;

public interface ICalculateStrategy {
    int calculateFee(Order order, int distance);
}
