package Recursion;

import java.util.*;
import java.io.*;

public class boj_1074
{
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        System.out.print(Z(n,r,c));

    }
    static int Z(int n, int r, int c)
    {
        //base condition
        if(n == 0) return 0;

        int half = 1 << (n-1);

        if(r < half && c < half) return Z(n-1,r,c); // 왼쪽 위
        if(r < half && c >= half) return half * half + Z(n-1, r, c-half); // 오른쪽 위
        if(r >= half && c < half) return 2 * (half * half) + Z(n-1, r - half, c); // 왼쪽 아래
        return 3 * (half * half) + Z(n-1, r-half, c-half); // 오른쪽 아래
    }
}

