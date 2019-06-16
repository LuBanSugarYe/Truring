package business.Admin.adminLogin;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import base.util.EncryptUtil;

public class AdminLogin {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
      String ab = EncryptUtil.md5("123456.891206@");
      System.out.println(ab);
      
      boolean abc=EncryptUtil.validPassword("123456.891206@", "65c88b932818053da12d1f613360a6ce");
      System.out.println(abc);
	}
}
