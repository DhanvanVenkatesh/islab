import java.util.*;

class CaesarCipher {
private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

public String encrypt(String plainText,int shiftKey)
{
	plainText = plainText.toLowerCase();
	String cipherText="";
	for(int i=0;i<plainText.length();i++)
	{
		int charPosition = ALPHABET.indexOf(plainText.charAt(i));
		int keyVal = (shiftKey+charPosition)%26;
		char replaceVal = this.ALPHABET.charAt(keyVal);
		cipherText += replaceVal;
	}
	return cipherText;
}

public String decrypt(String cipherText, int shiftKey)
{
	cipherText = cipherText.toLowerCase();
	String plainText="";
	for(int i=0;i<cipherText.length();i++)
	{
		int charPosition = this.ALPHABET.indexOf(cipherText.charAt(i));
		int keyVal = (charPosition-shiftKey)%26;
		if(keyVal<0)
		{
			keyVal = this.ALPHABET.length() + keyVal;
		}
		char replaceVal = this.ALPHABET.charAt(keyVal);
		plainText += replaceVal;
	}
	return plainText;
}

public void  decryptbrute(String cipherText)
{
	cipherText = cipherText.toLowerCase();
	for(int shiftKey =1; shiftKey<26; shiftKey++){
		String plainText="";
		for(int i=0;i<cipherText.length();i++){
			int charPosition = this.ALPHABET.indexOf(cipherText.charAt(i));
			int keyVal = (charPosition-shiftKey)%26;
			if(keyVal<0)
			{
				keyVal = this.ALPHABET.length() + keyVal;
			}
			char replaceVal = this.ALPHABET.charAt(keyVal);
			plainText += replaceVal;
		}
		System.out.println("For key value "+shiftKey+" the decrypted text is \""+plainText+"\"");
	}
}

}

class CaesarDemo {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String plainText;
		String cipherText;
		String cPlainText;
		boolean cond = true;
		int shiftKey, option;
		CaesarCipher cc = new CaesarCipher();
		while(cond){
			System.out.println("\n---------------------  Cesser Cipher  ---------------------\n");
			System.out.println("Options : \n1.Encrytion\n2.Decryption\n3.Brute Force Approach\n4.End");
			System.out.println("Enter your option : ");
			option = sc.nextInt();
			switch (option) {
    			case 1:
    				System.out.println("\n---------------------  Encryption  ---------------------\n");
    				System.out.println("Enter Your Plain Text : ");
					plainText = sc.next();
					System.out.println("Enter your key : ");
					shiftKey = sc.nextInt();
					cipherText = cc.encrypt(plainText,shiftKey);
					System.out.println("Your Plain Text :" + plainText);
					System.out.println("Your Cipher Text :" + cipherText);
    				break;
    			case 2: 
    				System.out.println("\n---------------------  Decryption  ---------------------\n");
    				System.out.println("Enter Your Cipher Text : ");
					cipherText = sc.next();
					System.out.println("Enter your key : ");
					shiftKey = sc.nextInt();
					System.out.println("Your Cipher Text :" + cipherText);
					cPlainText = cc.decrypt(cipherText,shiftKey);
					System.out.println("Your Plain Text :" + cPlainText);
    				break;
    			case 3:
    				System.out.println("\n---------------------  Brute Force Method  ---------------------\n");
    				System.out.println("Enter Your Cipher Text : ");
					cipherText = sc.next();
    				cc.decryptbrute(cipherText);
    				break;
    			case 4:
    				cond = false;
			}
		}
	}
}
