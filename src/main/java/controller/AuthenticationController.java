package controller;

import common.exception.ExpiredSessionException;
import common.exception.FailLoginException;
import dao.user.UserDAO;
import entity.user.User;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import utils.Cipher;


/**
 * @author
 */
public class AuthenticationController extends BaseController {

	// coincidental cohesion
	// phuong thuc khong lien quan den xac thuc
    public boolean isAnonymousSession() {
        try {
            getMainUser();
            return false;
        } catch (Exception ex) {
            return true;
        }
    }
// common coupling
    // su dung truc tiep cac thuoc tinh static cua SessionInformation
    // refactor 
//  public User getMainUser() throws ExpiredSessionException {
//  if (SessionInformation.mainUser == null || SessionInformation.expiredTime == null || SessionInformation.expiredTime.isBefore(LocalDateTime.now())) {
//      logout();
//      throw new ExpiredSessionException();
//  } else return SessionInformation.mainUser.cloneInformation();
//}
    
 // coincidental cohesion
 	// phuong thuc khong lien quan den xac thuc
public User getMainUser() throws ExpiredSessionException {
  if (SessionInformation.getInstance().getMainUser() == null || SessionInformation.getInstance().getExpiredTime() == null || SessionInformation.getInstance().getExpiredTime().isBefore(LocalDateTime.now())) {
      logout();
      throw new ExpiredSessionException();
  } else return SessionInformation.getInstance().getMainUser().cloneInformation();
}
// content coupling
// su dung thuoc tinh static cua SessionInfomation
// va gan gia tri thuoc tinh truc tiep cho user
// refactor
public void login(String email, String password) throws Exception {
	Cipher cipher = new Cipher();
  try {
      User user = new UserDAO().authenticate(email, cipher.md5Encrytion(password));
      if (Objects.isNull(user)) throw new FailLoginException();
//      SessionInformation.mainUser = user;
//      SessionInformation.expiredTime = LocalDateTime.now().plusHours(24);
      SessionInformation.getInstance().setMainUser(user);
    SessionInformation.getInstance().setExpiredTime(LocalDateTime.now().plusHours(24))  ;
    
  } catch (SQLException ex) {
      throw new FailLoginException();
  }
}

// content coupling
// su dung cac thuoc tinh static cua SessionInfomation
// va gan gia tri bang null
//refactor
public void logout() {
//  SessionInformation.mainUser = null;
//  SessionInformation.expiredTime = null;
	  SessionInformation.getInstance().setMainUser(null);
    SessionInformation.getInstance().setExpiredTime(null)  ;
}

/**
* Return a {@link String String} that represents the cipher text
* encrypted by md5 algorithm.
*
* @param message - plain text as {@link String String}.
* @return cipher text as {@link String String}.
*/

// Coincidental coupling
// phuong thuc khong lien quan den xac thuc nguoi dung
// refactor : chuyen sang class khac o package utils
//private String md5(String message) {
//  String digest = null;
//  try {
//      MessageDigest md = MessageDigest.getInstance("MD5");
//      byte[] hash = md.digest(message.getBytes(StandardCharsets.UTF_8));
//      // converting byte array to Hexadecimal String
//      StringBuilder sb = new StringBuilder(2 * hash.length);
//      for (byte b : hash) {
//          sb.append(String.format("%02x", b & 0xff));
//      }
//      digest = sb.toString();
//  } catch (NoSuchAlgorithmException ex) {
//      Utils.getLogger(Utils.class.getName());
//      digest = "";
//  }
//  return digest;
//}

}