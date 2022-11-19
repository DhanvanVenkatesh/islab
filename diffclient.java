import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;

public class diffclient {
static Scanner in = new Scanner(System.in);
static BigInteger one = new BigInteger("1");

static BigInteger powFinder(BigInteger a, BigInteger e, BigInteger p)
{
return a.modPow(e,p);
}

public static void main(String[] args) throws Exception{
    Socket soc = new Socket("127.0.10.10",1234);
    DataInputStream dis = new DataInputStream(soc.getInputStream());
    DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
    while (true)
    {
    System.out.print("\t\tOPTIONS\n 1.Key Exchange\n 2.Man-in-the-middle Attack\n 3.Exit\n Enter a option : ");
    int opt = in.nextInt();
    dos.writeInt(opt);
    if(opt == 1)
    {
        System.out.println("\n\tDiffie HellMan Key Exchange");
        String q_val = dis.readUTF();
        BigInteger q = new BigInteger(q_val);
        System.out.print("\nq = "+q_val);
        
        String alpha_val = dis.readUTF();
        BigInteger alpha = new BigInteger(alpha_val);
        System.out.print("\nalpha = "+alpha);
        
        
        BigInteger xb = getRandomBigInteger(128).nextProbablePrime();
        System.out.print("\nPrivate Key = "+ xb);
        
        BigInteger yb = alpha.modPow(xb, q);
        System.out.print("\nPublic Key = "+ yb);
        BigInteger ya = new BigInteger(dis.readUTF());
        dos.writeUTF(String.valueOf(yb));
        BigInteger k = ya.modPow(xb, q);
        System.out.println("\nShared Key = "+k);
    }
    
else if(opt == 2)
{

System.out.println("\tHello User "+"\n");
System.out.print("\nP = ");
BigInteger q= in.nextBigInteger();
System.out.print("\na = ");
BigInteger a = in.nextBigInteger();
System.out.print("\na = "+a);
BigInteger xb = getRandomBigInteger(128).nextProbablePrime();
System.out.print("\nprivate key = "+ xb);
BigInteger yb = powFinder(a,xb,q);
dos.writeUTF(String.valueOf(yb));
BigInteger ya = new BigInteger(dis.readUTF());
System.out.println("\nExchanged public key From the User : "+ya);
BigInteger k = powFinder(ya,xb,q);
System.out.println(" Shared Key : "+k+"\n\n");
}
}
}
public static BigInteger getRandomBigInteger(int bits) {
    Random rand = new Random();
    BigInteger result = new BigInteger(bits, rand); 
    return result;
}
}