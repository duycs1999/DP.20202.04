package subsystem;

import entity.payment.CreditCard;
import entity.payment.PaymentContext;
import entity.payment.PaymentTransaction;
import subsystem.interbank.InterbankSubsystemController;

/***
 * The {@code InterbankSubsystem} class is used to communicate with the
 * Interbank to make transaction.
 * 
 * @author hieud
 *
 */
public class InterbankSubsystem implements InterbankInterface {

	/**
	 * Represent the controller of the subsystem
	 */
	private InterbankSubsystemController ctrl;

	/**
	 * Initializes a newly created {@code InterbankSubsystem} object so that it
	 * represents an Interbank subsystem.
	 */
	public InterbankSubsystem() {
//		this.ctrl = new InterbankSubsystemController();
		//sua lai
		this.ctrl =  InterbankSubsystemController.getInstance();
	}

	/**
	 * @see InterbankInterface#payOrder(PaymentContext, int, String)
	 */

	// Vi phạm nguyên tắc OCP và DIP vì: tham số CreditCard là một đối tượng cụ thể nên Class đang bị phụ thuộc vào một lớp con cụ thể dẫn đến DIP, cùng với đó là khi mở rộng dùng những phương thức thanh toán khác thì chúng ta rất khó mở rộng và phải sửa đổi code nên vi phạm cả OCP.
		
	//vi pham nguyen ly OCD
	// khi them phuong thuc thanh toan moi thi phai thay doi code

	public PaymentTransaction payOrder(PaymentContext card, int amount, String contents) {
		PaymentTransaction transaction = ctrl.payOrder(card, amount, contents);
		return transaction;
	}

	/**
	 * @see InterbankInterface#refund(CreditCard, int,
	 *      String)
	 */
	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		PaymentTransaction transaction = ctrl.refund(card, amount, contents);
		return transaction;
	}
}
