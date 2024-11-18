package DP;
//가장 큰 증가하는 부분 수열
import java.io.*;
import java.util.StringTokenizer;
public class boj_11055
{
    //d[i] = arr[i]를 마지막 원소로 갖는 합이 가장 큰 부분 수열
    static int[] arr, d;
    public static void main(String[] args) throws IOException
    {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            arr = new int[n+1];
            d = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=n;i++)
            {
                arr[i] = Integer.parseInt(st.nextToken());
                d[i] = arr[i];
            }
            int ans = 0;
            for(int i=1;i<=n;i++)
            {
                for(int j=1;j<=i;j++)
                {
                    // 증가하는 부분 수열의 조건
                    if(arr[j] < arr[i])
                    {
                        d[i] = Math.max(d[i], d[j] + arr[i]);
                    }
                }
                ans = Math.max(ans, d[i]);
            }
            System.out.print(ans);
    }
}
