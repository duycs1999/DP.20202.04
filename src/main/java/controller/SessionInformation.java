package controller;

import entity.cart.Cart;
import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 */
public class SessionInformation {

    public static User mainUser;
    public static Cart cartInstance =  Cart.getInstance();
    public static LocalDateTime expiredTime;

}
