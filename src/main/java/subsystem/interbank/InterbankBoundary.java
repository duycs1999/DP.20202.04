package subsystem.interbank;

import common.exception.UnrecognizedException;
import utils.ApplicationProgrammingInterface;

public class InterbankBoundary {
	// ap dung Singleton
	//nghiep vu yeu cau chi can tao ra mot doi tuong duy nhat
	// pt query ton tai nguyen
private static InterbankBoundary instance=null;
	
	private InterbankBoundary() {
		
	}
	
	public static InterbankBoundary getInstance() {
		if (instance==null) instance =new InterbankBoundary();
		return instance;
	};
//


	String query(String url, String data) {
		String response = null;
		try {
			response = ApplicationProgrammingInterface.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}

}
