package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

//Lop Utils chua Logger ghi lai nhat ky cua he thong ma cac Logger nay chi nen co mot diem truy cap chung va khi thuc hien tac vu ghi nhat ky chi can tao mot doi tuong duy nhat cho nen phu hop voi Singleton

public class Utils {

	private static Utils _instance;
	private Utils() {

	}
	public static synchronized Utils getInstance() {
		if (_instance == null) {
			_instance = new Utils();
		}
		return _instance;
	}

	public static DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static Logger LOGGER = getLogger(Utils.class.getName());
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
	}

	public static Logger getLogger(String className) {
		return Logger.getLogger(className);
	}

}