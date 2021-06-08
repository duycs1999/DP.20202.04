package controller;

import entity.cart.Cart;
import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 */
public class SessionInformation {
//    public static User mainUser;
//    public static Cart cartInstance = new Cart();
//    public static LocalDateTime expiredTime;

	// refactor cho common coupling 
	private  User mainUser;
	  private  Cart cartInstance ;
	  private  LocalDateTime expiredTime;
	  private static SessionInformation instance;
	  public static SessionInformation getInstance () {
		  if (instance ==null) {
			  instance = new SessionInformation();
		  }
		  return instance;
	  }
	public User getMainUser() {
		return mainUser;
	}
	public void setMainUser(User mainUser) {
		this.mainUser = mainUser;
	}
	public Cart getCartInstance() {
		return cartInstance;
	}

	public LocalDateTime getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(LocalDateTime expiredTime) {
		this.expiredTime = expiredTime;
	}
}
