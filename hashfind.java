import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class hashfind {
	static HashMap<String, String> dict = new HashMap<>();
	static HashMap<String, Integer> user = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

	public static byte[] getSHA(String input) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(input.getBytes(StandardCharsets.UTF_8));
	}
	
	public static String toHexString(byte[] hash){
		BigInteger number = new BigInteger(1, hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));
		while (hexString.length() < 64){
			hexString.insert(0, '0');
		}
		return hexString.toString();
	}

	public static void combinations() throws NoSuchAlgorithmException {
        for(int i=97; i<=122; i++){
            for(int j=97; j<=122; j++){
                for(int k=97; k<=122; k++){
                    char a,b,c;
                    a = (char)i;
                    b = (char)j;
                    c = (char)k;
                    String val = new StringBuilder().append(a).append(b).append(c).toString();
                    String enc = toHexString(getSHA(val));
					dict.put(enc, val);
                }
            }
        }
    }

	public static void main(String args[]) throws NoSuchAlgorithmException{
        String enc;
		int id;
		boolean cond = true;
		combinations();
		while(cond){
			System.out.println("\nOPTIONS:\n1.User Details\n2.Hash to password\n3.End\n");
			int opt = sc.nextInt();
			switch(opt){
				case 1:
					System.out.println("Enter User ID : ");
					id = sc.nextInt();
					System.out.println("Enter the password : ");
					enc = sc.next();
					user.put(enc,id);
					break;
				case 2:
					System.out.println("Enter the hash : ");
					enc = sc.next();
					String val = dict.get(enc);
					System.out.println("User ID : "+user.get(val));
					System.out.println("Password : "+val);
					break;
				case 3:
					cond = false;
					break;
			}
		}	
	}
}
