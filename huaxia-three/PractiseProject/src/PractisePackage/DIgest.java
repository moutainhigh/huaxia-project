package PractisePackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DIgest {

	public DIgest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String algname = "SHA-1";
		try {
			MessageDigest alg = MessageDigest.getInstance(algname);
//			byte[] str = "Hello World!";
//			String result = alg.digest(str);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
