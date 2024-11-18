package DP;
import java.io.*;
import java.util.StringTokenizer;
public class boj_1932
{
    static int n;
    static int[][] a;
    static int[][] d; //i번째 줄의 j번째 요소까지의 경로에서 엳을 수 있는 최대 합
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        a = new int[n+1][n+1];
        d = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=i; j++) // i번째 줄에는 i개의 요소만 존재하므로 범위를 i이하까지로 제한
            {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        d[1][1] = a[1][1];

       for(int i=2; i<=n; i++)
       {
           for(int j=1; j<=i; j++)
           {
               // 왼쪽 가장자리
               if(j == 1)
               {
                   d[i][j] = d[i-1][j] + a[i][j];
               }
               // 오른쪽 가장자리
               else if(j == i)
               {
                   d[i][j] = d[i-1][j-1] + a[i][j];
               }
               // 중간 요소들
               else
               {
                   d[i][j] = Math.max(d[i-1][j-1], d[i-1][j]) + a[i][j];
               }
           }
       }
       int max = 0;
       for(int j=1; j<=n; j++)
       {
           max = Math.max(max, d[n][j]);
       }
       System.out.print(max);
    }
}
