import java.util.*;

public class jfactor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter value of n :");
        int n = sc.nextInt();
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (i % 2 != 0) {
                al.add(i);

            }
        }
        int p = 0;
        int q = 0;
        for (int i = 0; i < al.size(); i++) {
            for (int j = 1; j < al.size(); j++) {
                if (al.get(i) * al.get(j) == n) {
                    p = al.get(i);
                    q = al.get(j);

                }

            }
        }
        System.out.println(p);
        System.out.println(q);
    }

}