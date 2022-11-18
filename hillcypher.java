import java.util.*;

public class hillcypher {
    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    static int N;
    static int cc;

    static int[][] txt_matrix(String text, int size) {
        text = text.toLowerCase();
        int len = text.length();
        int col, a = 0;
        for (int i = 0; i < (len % size); i++) {
            if (len % size != 0) {
                text += "x";
                len = text.length();
            }
        }
        col = len / size;
        cc = col;
        int[][] ans = new int[size][col];

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < size; j++) {
                ans[j][i] = ALPHABET.indexOf(text.charAt(a));
                a++;
            }
        }
        return ans;
    }

    static int[][] matrix_mult(int arr1[][], int arr2[][], int p, int q) {
        int[][] ans = new int[p][q];
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < q; j++) {
                for (int k = 0; k < p; k++) {
                    ans[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        return ans;
    }

    static String decode(int enc[][], int p, int q) {
        String text = "";
        for (int i = 0; i < q; i++) {
            for (int j = 0; j < p; j++) {
                enc[j][i] = enc[j][i] % 26;
                text += ALPHABET.charAt(enc[j][i]);
            }
        }
        return text;
    }

    static void getCofactor(int A[][], int temp[][], int p, int q, int n) {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = A[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    static int euclid(int val) {
        int q, a, b, r, i = 0;
        int t1, t2, t;
        List<Integer> arr = new ArrayList<Integer>();
        if (val > 26) {
            a = val;
            b = 26;
        } else {
            a = 26;
            b = val;
        }
        do {
            q = a / b;
            r = a % b;
            arr.add(q);
            a = b;
            b = r;
        } while (b != 0);
        arr.add(a);
        if (arr.get(arr.size() - 1) == 1) {
            t1 = 0;
            t2 = 1;
            for (i = 0; i < arr.size(); i++) {
                t = t1 - (arr.get(i) * t2);
                if (26 != Math.abs(t2)) {
                    t1 = t2;
                    t2 = t;
                }
            }
            return t1;
        } else {
            return -1;
        }

    }

    static int determinant(int A[][], int n) {
        int D = 0;
        if (n == 1)
            return A[0][0];
        int[][] temp = new int[N][N];
        int sign = 1;
        for (int f = 0; f < n; f++) {
            getCofactor(A, temp, 0, f, n);
            D += sign * A[0][f] * determinant(temp, n - 1);
            sign = -sign;
        }
        return D;
    }

    static void adjoint(int A[][], int[][] adj) {
        if (N == 1) {
            adj[0][0] = 1;
            return;
        }
        int sign = 1;
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                getCofactor(A, temp, i, j, N);
                sign = ((i + j) % 2 == 0) ? 1 : -1;
                adj[j][i] = (sign) * (determinant(temp, N - 1));
            }
        }
    }

    static boolean inverse(int A[][], int[][] inverse) {
        int det = determinant(A, N);
        int euc = euclid(det);
        if (euc == -1) {
            System.out.print("Not Possible");
            return false;
        }
        System.out.println();

        if (det == 0) {
            System.out.print("Singular matrix, can't find its inverse");
            return false;
        }
        int[][] adj = new int[N][N];
        adjoint(A, adj);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                inverse[i][j] = adj[i][j] * euc;
        return true;
    }

    static int[][] inverse_mod(int[][] arr) {
        int val, ans;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                val = arr[i][j];
                ans = val % 26;
                if (ans < 0) {
                    ans += 26;
                }
                arr[i][j] = ans;
            }
        }
        return arr;
    }

    static void encrypt(String text, int[][] arr, int size) {
        String enc_text = "";
        int len = text.length();
        int col, a = 0;
        col = len / size;
        int[][] ans = new int[size][col];
        int[][] enc = new int[size][col];
        ans = txt_matrix(text, size);
        enc = matrix_mult(arr, ans, size, cc);
        System.out.println();
        enc_text = decode(enc, size, cc);
        System.out.println("Encryted Text : " + enc_text);
    }

    static void decrypt(String text, int[][] arr, int size) {
        String dec_text = "";
        int len = text.length();
        int col, a = 0;
        col = len / size;
        int[][] ans = new int[size][col];
        int[][] enc = new int[size][col];
        int[][] adj = new int[N][N];
        int[][] inv = new int[N][N];
        int[][] fin = new int[N][N];
        ans = txt_matrix(text, size);
        adjoint(arr, adj);
        if (inverse(arr, inv)) {
            fin = inverse_mod(inv);
            enc = matrix_mult(fin, ans, size, col);
            System.out.println();
            dec_text = decode(enc, size, col);
            System.out.println("Decryted Text : " + dec_text);
        }
    }

    static void known(String text1, String text2, int size) {
        String dec_text = "";
        int len1 = text1.length();
        int len2 = text2.length();
        int col, a = 0;
        int[][] arr1 = new int[size][size];
        int[][] arr2 = new int[size][size];
        int[][] enc = new int[size][size];
        int[][] adj = new int[N][N];
        int[][] inv = new int[N][N];
        int[][] fin = new int[N][N];
        arr1 = txt_matrix(text1, size);
        arr2 = txt_matrix(text2, size);
        adjoint(arr1, adj);
        if (inverse(arr1, inv)) {
            fin = inverse_mod(inv);
            enc = matrix_mult(arr2, fin, size, size);
            System.out.println();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(enc[i][j] % 26 + "   ");
                }
                System.out.println();
            }
            System.out.println("Decryted Text : " + dec_text);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text, str1, str2;
        int k_ord, option;
        boolean cond = true;
        System.out.println("\nEnter Key Matrix Order : ");
        k_ord = sc.nextInt();
        N = k_ord;
        int[][] arr = new int[k_ord][k_ord];
        System.out.println("\nEnter The Elements : ");
        for (int i = 0; i < k_ord; i++) {
            for (int j = 0; j < k_ord; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        while (cond) {
            System.out.println("\n---------------------  Hill Cipher  ---------------------\n");
            System.out.println("Options : \n1.Encrytion\n2.Decryption\n3.Known PT-CT Attack\n4.End");
            System.out.println("Enter your option : ");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("\n---------------------  Encryption  ---------------------\n");
                    System.out.println("\nEnter Plain Text : ");
                    text = sc.next();
                    encrypt(text, arr, k_ord);
                    break;
                case 2:
                    System.out.println("\n---------------------  Decryption  ---------------------\n");
                    System.out.println("\nEnter Cipher Text : ");
                    text = sc.next();
                    decrypt(text, arr, k_ord);
                    break;
                case 3:
                    System.out.println("\n---------------------  Known PT-CT Attack  ---------------------\n");
                    System.out.println("\nEnter the input in multiple of " + k_ord * k_ord);
                    System.out.println("\nEnter Plain Text : ");
                    str1 = sc.next();
                    System.out.println("\nEnter Cipher Text : ");
                    str2 = sc.next();
                    known(str1, str2, k_ord);
                    break;
                case 4:
                    cond = false;
            }
        }
    }
}