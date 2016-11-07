import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Crypto {
	
	static String[] hashPW (String pw)
	{
		String saltedpw;
		String[] res = new String[2];
		res[0] = getSalt();
		saltedpw = res[0] + pw;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(saltedpw.getBytes());
			byte byteData[] = digest.digest();
			StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	           sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        res[1] = sb.toString();        
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;

		
	}
	
	static boolean authUser (String pw, String salt, String encrypredpw)
	{
		boolean returnValue = false;
		String saltedpw = salt + pw;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(saltedpw.getBytes());
			byte byteData[] = digest.digest();
			StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	           sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        if (sb.toString().equals(encrypredpw)) {
	        	returnValue = true;
	        }
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;

		
	}
	
	
	public static String getSalt(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String salt = dateFormat.format(date);
		return salt;
		
	}

}
