package entity.cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.exception.MediaNotAvailableException;
import entity.media.Media;

public class Cart {
    
	private List<CartItem> lstCartItem;

	 //   public Cart() {
	// implement singleton pattern
	    private Cart() {
	        lstCartItem = new ArrayList<>();
	    }
	    //
	    private static Cart cartInstance;

	    public static Cart getInstance(){
	        if(cartInstance==null){
	            cartInstance = new Cart();
	        }
	        return cartInstance;
	    }
	    //

    public void addCartMedia(CartItem cm){
        lstCartItem.add(cm);
    }

    public void removeCartMedia(CartItem cm){
        lstCartItem.remove(cm);
    }

    public List getListMedia(){
        return lstCartItem;
    }

    public void emptyCart(){
        lstCartItem.clear();
    }

    public int getTotalMedia(){
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cm = (CartItem) obj;
            total += cm.getQuantity();
        }
        return total;
    }

    public int calSubtotal(){
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cm = (CartItem) obj;
            total += cm.getPrice()*cm.getQuantity();
        }
        return total;
    }

    public void checkAvailabilityOfProduct() throws SQLException{
        boolean allAvailable = true;
        for (Object object : lstCartItem) {
            CartItem cartItem = (CartItem) object;
            int requiredQuantity = cartItem.getQuantity();
            int availQuantity = cartItem.getMedia().getQuantity();
            if (requiredQuantity > availQuantity) allAvailable = false;
        }
        if (!allAvailable) throw new MediaNotAvailableException("Some media not available");
    }

 // stamp coupling
    // phuong thuc chi su dung ket qua phuong thuc getId() cua doi tuong media
//    public CartItem checkMediaInCart(Media media){
//        for (CartItem cartItem : lstCartItem) {
//            if (cartItem.getMedia().getId() == media.getId()) return cartItem;
//        }
//        return null;
//    }
    public CartItem checkMediaInCart(int id){ //id=media.getId()
        for (CartItem cartItem : lstCartItem) {
            if (cartItem.getMedia().getId() == id) return cartItem;
        }
        return null;
    }

}
