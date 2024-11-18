package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//연속합
//d[i] = i번째까지의 최대 연속 부분합
public class boj_1912
{
    static int[] arr, d;
    public static void main(String[] args) throws IOException
    {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            arr = new int[n+1];
            d = new int[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++)
            {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            br.close();
            d[1] = arr[1];
            int max = d[1];
            for(int i=2; i <= n; i++)
            {
                // arr[i]를 선택하는 경우 = 새로운 연속합을 시작
                // 이전 최대 연속합에 현재값을 더한 값이 arr[i] 보다 큰 경우 이전 최대 연속합에 arr[i] 값을 누적
                d[i] = Math.max(arr[i] , d[i-1] + arr[i]);
                max = Math.max(max, d[i]);
            }
            System.out.println(max);
    }
}
