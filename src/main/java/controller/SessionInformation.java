package controller;

import entity.cart.Cart;
import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 */
public class SessionInformation {

//    public static User mainUser;
//    public static Cart cartInstance =  Cart.getInstance();
//    public static LocalDateTime expiredTime;

    public static User mainUser;
    private static final Cart cartInstance = new Cart();
    public static Cart getInstance() {
        return cartInstance;
    }
    public static LocalDateTime expiredTime;

}
