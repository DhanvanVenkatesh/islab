import java.util.Scanner;

public class SHA {
    static String boolfunc(String m, String n, String o) {
        long a = Long.parseLong(m, 16);
        long b = Long.parseLong(n, 16);
        long c = Long.parseLong(o, 16);
        long d = (a & b) | (~a & c);
        String res = Long.toHexString(d);
        return res;
    }

    static String add(String a, String b) {
        int i, j;
        String res1 = "";
        String res2 = "";
        for (i = 1; i < a.length(); i++) {
            res1 = res1 + a.charAt(i);
        }
        for (j = 1; j < b.length(); j++) {
            res2 = res2 + b.charAt(j);
        }
        String res3 = Integer.toHexString(Integer.parseInt(res1, 16) + Integer.parseInt(res2, 16));
        String fin = Integer.toHexString(
                Integer.parseInt(String.valueOf(a.charAt(0)), 16) + Integer.parseInt(String.valueOf(b.charAt(0)), 16))
                + res3;
        int k;
        String res = "";
        if (fin.length() > 8) {
            for (k = 1; k < fin.length(); k++) {
                res = res + fin.charAt(k);
            }
        } else {
            res = fin;
        }
        return res;
    }

    static String shiftfive(String a) {
        int s = Integer.parseInt(a, 16);
        String binary = Integer.toBinaryString(s);
        int j;
        if (binary.length() < 32) {
            String padding = "";
            for (j = 0; j < (32 - binary.length()); j++) {
                padding = padding + "0";
            }
            binary = padding + binary;
        }
        String res = binary.substring(5) + binary.substring(0, 5);
        int i;
        String temp;
        String result = "";
        int b = 0;
        int e = 4;
        for (i = 0; i < 8; i++) {
            temp = res.substring(b, e);
            int decimalNumber = Integer.parseInt(temp, 2);
            String hexNumber = Integer.toString(decimalNumber, 16);
            b = b + 4;
            e = e + 4;
            result = result + hexNumber;
        }
        System.out.println("Shift five  " + result);
        return result;
    }

    static String shiftthirty(String a) {
        long s = Long.parseLong(a, 16);
        String binary = Long.toBinaryString(s);
        int j;
        if (binary.length() < 32) {
            String padding = "";
            for (j = 0; j < (32 - binary.length()); j++) {
                padding = padding + "0";
            }
            binary = padding + binary;
        }
        String res = binary.substring(30) + binary.substring(0, 30);
        int i;
        String temp;
        String result = "";
        int b = 0;
        int e = 4;
        for (i = 0; i < 8; i++) {
            temp = res.substring(b, e);
            int decimalNumber = Integer.parseInt(temp, 2);
            String hexNumber = Integer.toString(decimalNumber, 16);
            b = b + 4;
            e = e + 4;
            result = result + hexNumber;
        }
        System.out.println("Shift thirty  " + result);
        return result;
    }

    public static void main(String[] args) {
        String a = "67452301";
        String b = "efcdab89";
        String c = "98badcfe";
        String d = "10325476";
        String e = "c3d2e1f0";
        String rk = "5a827999";
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine();
        String word = inp.toUpperCase();
        int i;
        String wt = "";
        for (i = 0; i < 4; i++) {
            int temp = (int) word.charAt(i);
            wt = wt + Integer.toHexString(temp);
        }
        String fun = boolfunc(b, c, d);
        String addres = add(fun, e);
        String shif = add(addres, shiftfive(a));
        String wts = add(shif, wt);
        String sht = add(wts, rk);
        e = d;
        d = c;
        c = shiftthirty(b);
        b = a;
        a = sht;
        System.out.println("a: " + a + " b: " + b + " c: " + c + " d: " + d + " e: " + e);
    }
}
