package subsystem.interbank;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

public class InterbankSubsystemController {
	
	// ap dung Singleton
	// yeu cau nghiep vu chi can tao mot doi tuong
	private static InterbankSubsystemController instance=null;
		
		private InterbankSubsystemController() {
			
		}
		
		public static InterbankSubsystemController getInstance() {
			if (instance==null) instance =new InterbankSubsystemController();
			return instance;
		};
		private static InterbankPayloadConverter interbankPayloadConverter =  InterbankPayloadConverter.getInstance();
		private static InterbankBoundary interbankBoundary =  InterbankBoundary.getInstance();
		//

//	private static InterbankPayloadConverter interbankPayloadConverter = new InterbankPayloadConverter();
//	private static InterbankBoundary interbankBoundary = new InterbankBoundary();

	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		return null;
	}
// vi pham nguyen ly OCD
	// khi them mot phuong thuc thanh toan moi thi phai thay doi code
	public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
		String requestPayload = interbankPayloadConverter.convertToRequestPayload(card, amount, contents);
		String responseText = interbankBoundary.query(InterbankConfigs.PROCESS_TRANSACTION_URL, requestPayload);
		return interbankPayloadConverter.extractPaymentTransaction(responseText);
	}

}
