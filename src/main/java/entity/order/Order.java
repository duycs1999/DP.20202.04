package entity.order;

import controller.SessionInformation;
import entity.cart.Cart;
import entity.cart.CartItem;
import entity.shipping.DeliveryInfo;
import views.screen.ViewsConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import entity.state.CancelState;
import entity.state.OrderState;
import common.interfaces.State;

public class Order {

    private int shippingFees;
    private int subtotal;
    private int tax;
    private List orderMediaList;
    protected DeliveryInfo deliveryInfo;

    private  State state;

    public Order() {
        this.shippingFees = 0;
        this.subtotal = 0;
        this.tax = 0;
        this.state = new HoldOn(this);
    }
    public void changeState(State state) {
        this.state = state;
    }
    public void cancelOrder() {
        state.doCancel();
    }
    public void ApprovalOrder() {
        state.doApproval();
    }
    public Order(Cart cart) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Object object : SessionInformation.cartInstance.getListMedia()) {
            CartItem cartItem = (CartItem) object;
            OrderItem orderItem = new OrderItem(cartItem.getMedia(),
                    cartItem.getQuantity(),
                    cartItem.getPrice());
            orderItems.add(orderItem);
        }
        this.orderMediaList = Collections.unmodifiableList(orderItems);
        this.subtotal = cart.calSubtotal();                               // stamp coupling, truyá»�n Ä‘á»‘i tÆ°á»£ng cart 
                                                                          // nhÆ°ng chá»‰ sá»­ dá»¥ng calSubtotal();
        this.tax = (int) (ViewsConfig.PERCENT_VAT/100) * subtotal;
    }

    public void cancelOrder() {
        this.changeState(new CancelState());
    }

    public List getListOrderMedia() {
        return this.orderMediaList;
    }
// coincidental cohesion do khong lien quan den class
    public int getShippingFees() {
        if (deliveryInfo == null) return 0;
        return this.shippingFees;
    }
 // coincidental cohesion do khong lien quan den class
    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }
 // coincidental cohesion do khong lien quan den class
    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
        this.shippingFees = deliveryInfo.calculateShippingFee(this);
    }

    public List getOrderMediaList() {
        return orderMediaList;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public int getTax() {
        return tax;
    }
 // coincidental cohesion do khong lien quan den class
    public int getTotal() {
        return this.subtotal + this.tax + this.shippingFees;
    }
}
