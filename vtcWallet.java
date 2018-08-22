import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class vtcWallet 
{
	public static void main (String [] args) throws NoSuchAlgorithmException
	{
		String pk = "179e2e98d8989c31bd4be7d75a5ebfc9183735dd53cb2b60a4c25f8fd097651b";
		String pk80 = "80" + pk;
		//System.out.println(pk80);

		String hash = sha256(pk80);
		//System.out.println(hash);
		String doublehash = sha256(hash);
		//System.out.println(doublehash);
		
		String firsteight = doublehash.substring(0,8);
		//System.out.println(firsteight);
		
		String concat = pk80+firsteight;
		System.out.println(concat);
		
		decToBase58(concat);
		
	}
	
	static String sha256 (String input) throws NoSuchAlgorithmException
	{
		byte [] in = hexStringToByteArray(input);
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");	
		byte [] result = mDigest.digest(in);
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i<result.length; i++)
		{
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
	
	public static byte[] hexStringToByteArray (String s)
	{
		int len = s.length();
		byte [] data = new byte[len/2];
		for(int i = 0; i<len; i+=2)
		{
			data[i/2] = (byte)((Character.digit(s.charAt(i),16) << 4) + Character.digit(s.charAt(i+1),16));
		}
		return data;
	}
	
	public static void decToBase58 (String concat)
	{
		BigInteger num = hexToDecimal(concat);
		BigInteger x = new BigInteger("58");
		BigInteger rem = new BigInteger("0");
		String walletPK = "";
		
		char base58[] = {'1','2','3','4','5','6','7','8','9',
						'A','B','C','D','E','F','G','H','J',
						'K','L','M','N','P','Q','R','S','T',
						'U','V','W','X','Y','Z','a','b','c',
						'd','e','f','g','h','i','j','k','m','n',
						'o','p','q','r','s','t','u','v','w','x','y','z'};
		
		while(num.bitLength()>0)
		{
			rem = num.mod(x);
			int r = rem.intValue();
			walletPK = base58[r]+walletPK;
			num=num.divide(x);
		}
		System.out.println(walletPK);
	}
	
	public static BigInteger hexToDecimal (String concat)
	{
		String hex = "0123456789ABCEDEF";
		concat=concat.toUpperCase();
		BigInteger sixteen = new BigInteger("16");
		BigInteger dec = new BigInteger("0");
		
		for(int i = 0; i<concat.length(); i++)
		{
			char c = concat.charAt(i);
			int d = hex.indexOf(c);
			BigInteger bi = new BigInteger(String.valueOf(d));
			dec = (dec.multiply(sixteen)).add(bi);
		}
		return dec;
	}
}


