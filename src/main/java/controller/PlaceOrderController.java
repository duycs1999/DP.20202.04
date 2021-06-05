package controller;

import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;

import entity.shipping.DeliveryInfo;

import org.example.DistanceCalculator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import tools.Validate;

/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */

// Vi phạm SRP vì lớp này làm hơn 1 nhiệm vụ của nó, thêm những phương thức validate trong nó


 // Coincidental Cohesion do cac phuong thuc validate khong lien quan trong qua trinh Order,
// cac phuong thuc validate nen dat trong lop khac

// Procedural Cohesion do cac phuong thuc duoc nhom lai vi chung thuc thi theo trinh tu

 //SOLID: Vi phạm nguyên lý OCP vì khi số lượng dữ liệu cần xác thực thay đổi thì sẻ phải sửa code trong này

public class PlaceOrderController extends BaseController {

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getInstance().getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the availability of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException {
        SessionInformation.cartInstance.checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException {
        return new Order(SessionInformation.cartInstance);
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public DeliveryInfo processDeliveryInfo(HashMap info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
        Validate validate = new Validate();
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validate.validateDeliveryInfo(info);
        DeliveryInfo deliveryInfo = new DeliveryInfo(
                String.valueOf(info.get("name")),
                String.valueOf(info.get("phone")),
                String.valueOf(info.get("province")),
                String.valueOf(info.get("address")),
                String.valueOf(info.get("instructions")),
                new DistanceCalculator());
        System.out.println(deliveryInfo.getProvince());
        return deliveryInfo;
    }

}
