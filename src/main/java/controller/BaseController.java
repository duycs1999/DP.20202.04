package controller;

import java.util.List;

import entity.cart.Cart;
import entity.cart.CartItem;
import entity.media.Media;

/**
 * This class is the base controller for our AIMS project
 * @author nguyenlm
 */
public class BaseController {
    
    /**
     * The method checks whether the Media in Cart, if it were in, we will return the CartMedia else return null
     * @param media
     * @return CartMedia or null
     */
	
	// common coupling
		// su dung truc tiep cac thuoc tinh static cua class SessionInformation
//	    public CartItem checkMediaInCart(Media media){
//	       // return SessionInformation.cartInstance.checkMediaInCart(media);
//	    	return Cart.getInstance().checkMediaInCart(media);
//	    }
	    public CartItem checkMediaInCart(int id){
		       // return SessionInformation.cartInstance.checkMediaInCart(media);
		    	return Cart.getInstance().checkMediaInCart(id);
		    }

	    /**
	     * This method gets the list of items in cart
	     * @return List[CartMedia]
	     */
	    public List getListCartMedia(){
	       // return SessionInformation.cartInstance.getListMedia();
	    	return Cart.getInstance().getListMedia();
	    }
}
