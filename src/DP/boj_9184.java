package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_9184
{
    static int[][][] d = new int[21][21][21];
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while (true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1 && c == -1) break;

            sb.append("w(" + a + ", " + b + ", " + c + ") = ").append(w(a ,b ,c)).append('\n');
        }

        System.out.print(sb);
    }

    static int w(int a, int b, int c)
    {
        if(isRange(a, b, c) && d[a][b][c] != 0) return d[a][b][c];

        if(a <= 0 || b <= 0 || c <= 0) return 1;

        if(a > 20 || b > 20 || c > 20) return d[20][20][20] = w(20,20,20);

        if(a < b && b < c) return d[a][b][c] = w(a,b,c-1) + w(a,b-1,c-1) - w(a,b-1,c);

        return d[a][b][c] = w(a-1,b,c) + w(a-1,b-1,c) + w(a-1,b,c-1) - w(a-1, b-1, c-1);

    }

    static boolean isRange(int a, int b, int c)
    {
        return a >= 0 && a <= 20 && b >= 0 && b <= 20 && c >= 0 && c <= 20;
    }
}
