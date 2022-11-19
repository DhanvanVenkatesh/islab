import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class diffserver {
static Scanner in = new Scanner(System.in);

static BigInteger powFind(BigInteger a, BigInteger e, BigInteger p)
{
return (a.modPow(e,p));
}
public static void main(String []args) throws Exception
{
ServerSocket ss = new ServerSocket(1234);
Socket soc = ss.accept();
DataInputStream dis = new DataInputStream(soc.getInputStream());
DataOutputStream dos = new DataOutputStream(soc.getOutputStream());

while(true)
{
int opt = dis.readInt();
if (opt == 1)
{
    System.out.println("\n\tDiffie HellMan Key Exchange");
    BigInteger q =  getRandomBigInteger(256).nextProbablePrime();
    System.out.print("\nq = "+q);
    dos.writeUTF(String.valueOf(q));
    
    BigInteger alpha =  getRandomBigInteger(256).nextProbablePrime();
    System.out.print("\na = "+alpha);
    dos.writeUTF(String.valueOf(alpha));
    
    BigInteger xa =  getRandomBigInteger(128).nextProbablePrime();
    System.out.print("\nPrivate Key = "+ xa);
    
    BigInteger ya = alpha.modPow(xa, q);
    System.out.print("\nPublic Key = "+ ya);
    dos.writeUTF(String.valueOf(ya));
    
    BigInteger yb = new BigInteger(dis.readUTF());
    BigInteger k = yb.modPow(xa, q);
    System.out.println("\nShared secret Key = " + k);
}

else if (opt == 2)
{
System.out.println("User A has joined...");
System.out.println("Waiting for User B...");
Socket socket = ss.accept();
DataInputStream dis1 = new DataInputStream(socket.getInputStream());
DataOutputStream dos1 = new DataOutputStream(socket.getOutputStream());
if(dis1.readInt() == 2)
{
System.out.println("User B is connected...");
System.out.println("\n\tMan in the Middle Attack");

BigInteger q = getRandomBigInteger(256).nextProbablePrime();
System.out.print("\nP = " + q);
BigInteger a= q.nextProbablePrime();
System.out.print("\n A= " + a);

BigInteger xc= getRandomBigInteger(128).nextProbablePrime();
System.out.print("\nXc = "+xc);

BigInteger xd= getRandomBigInteger(128).nextProbablePrime();
System.out.print("\nXd = "+ xd);

BigInteger ya = new BigInteger(dis.readUTF());
System.out.println("\nThe Public Key from User A : " + ya);
BigInteger yb = new BigInteger(dis1.readUTF());
System.out.println("The Public Key from User B : " + yb);
BigInteger yaDash = powFind(a, xc, q);
BigInteger ybDash = powFind(a, xd, q);
dos.writeUTF(String.valueOf(yaDash));
dos1.writeUTF(String.valueOf(ybDash));

BigInteger key_a = ya.modPow(xc, q);
BigInteger key_b = yb.modPow(xd, q);

System.out.println("secret key A: "+key_a);
System.out.println("secret key of B : "+ key_b);

System.out.println("The key values are changed and sent to each other ");
}
}
}
}

public static BigInteger getRandomBigInteger(int bits) {
    Random rand = new Random();
    BigInteger result = new BigInteger(bits, rand); 
    return result;
}
}