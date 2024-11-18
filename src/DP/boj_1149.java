package DP;
import java.io.*;
import java.util.*;
//d[i][color] = i번째 집까지 칠할 때 비용의 최솟값, 단 i번째 집은 color로 칠해야 함
public class boj_1149
{
    static int[][] arr;
    static int[] r,g,b;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n+1][3];
        r = new int[n+1];
        g = new int[n+1];
        b = new int[n+1];
        StringTokenizer st;
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            r[i] = Integer.parseInt(st.nextToken());
            g[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
        }
        if(n==1)
        {
            System.out.print(Math.min(Math.min(r[1],g[1]),b[1]));
            return;
        }
        arr[1][0] = r[1];
        arr[1][1] = g[1];
        arr[1][2] = b[1];
        for(int i=2; i<=n; i++)
        {
            arr[i][0] = Math.min(arr[i-1][1], arr[i-1][2]) + r[i];
            arr[i][1] = Math.min(arr[i-1][0], arr[i-1][2]) + g[i];
            arr[i][2] = Math.min(arr[i-1][0], arr[i-1][1]) + b[i];
        }
        System.out.print(Math.min(Math.min(arr[n][0],arr[n][1]), arr[n][2]));
    }
}
