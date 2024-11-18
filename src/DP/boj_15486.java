package DP;
//퇴사2
import java.io.*;
import java.util.*;
public class boj_15486
{
    static int n, ans = 0;
    static int[] time, pay;
    static int[] d;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        time = new int[n+1];
        pay = new int[n+1];
        StringTokenizer st;
        for(int i = 1; i <= n; i++)
        {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }
        d = new int[n+2]; // n+1인덱스를 참조해야하기 때문에 n+2로 크기 설정
        for(int i=n; i>=1; i--)
        {
            if(i + time[i] <= n+1)
            {
                d[i] = Math.max(d[i + time[i]] + pay[i], d[i + 1]);
            }
            else
            {
                d[i] = d[i+1];
            }
            ans = Math.max(ans, d[i]);
        }
        System.out.print(ans);
    }
}
