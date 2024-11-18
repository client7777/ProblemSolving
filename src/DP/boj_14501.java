package DP;
//퇴사
import java.io.*;
import java.util.*;
public class boj_14501
{
    static int[] d; // d[i] = i번째 일에 상담을 시작했을 때 얻을 수 있는 최대 수익
    static int[] time, pay;
    static int n, ans;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        d = new int[20];
        time = new int[20];
        pay = new int[20];
        StringTokenizer st;
        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=n; i >= 1; i--)
        {
            // i번째 일에 상담을 할 수 있는 경우
            // n+1미만이 되어버리면 n일째에 시작할 수 있는 상담을 고려하지 않게 된다.
            if(i + time[i] <= n+1)
            {
                // i번째 일에 상담을 했을 때와 상담을 하지 않았을 때 얻을 수 있는 수익 중 최대 수익을 취함
                d[i] = Math.max(d[i + time[i]] + pay[i], d[i + 1]);
            }
            else
            {
                d[i] = d[i + 1];
            }
            ans = Math.max(ans, d[i]);
        }
        System.out.print(ans);
    }
}
