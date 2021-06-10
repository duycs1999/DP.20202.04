package controller;

import common.exception.InvalidDeliveryInfoException;
import entity.cart.Cart;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.shipping.AdapterAlternativeDistanceCalculator;
import entity.shipping.DeliveryInfo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import utils.Validate;

/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */
public class PlaceOrderController extends BaseController {

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the availability of product when user click PlaceOrder button
     * @throws SQLException
     */
 // common coupling
    // su dung truc tiep cac thuoc tinh static cua class SessionInformation
    
    public void placeOrder() throws SQLException {
       // SessionInformation.cartInstance.checkAvailabilityOfProduct();
    	Cart.getInstance().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    
    // common coupling 
 // su dung truc tiep cac thuoc tinh static cua class SessionInformation
    
    public Order createOrder() throws SQLException {
        //return new Order(SessionInformation.cartInstance);
    	//return new Order(Cart.getInstance());
    	return new Order(Cart.getInstance().calSubtotal());
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
                new AdapterAlternativeDistanceCalculator());
        System.out.println(deliveryInfo.getProvince());
        return deliveryInfo;
    }

}