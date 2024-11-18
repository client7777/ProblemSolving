package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//이친수
//int로 정수범위를 지정할 경우 n이 커짐에 따라 출력값이 빠르게 증가해서 범위를 벗어날 수 있음
public class boj_2193
{
    static int n;
    static long d[][];
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        d = new long[n+1][2];
        // d[i][0] = i자리 이친수 중 끝자리가 0인 경우의 개수, d[i][1] = i자리 이친수 중 끝자리가 1인 경우의 개수

        d[1][1] = 1;
        d[1][0] = 0;

        for(int i=2;i<=n;i++)
        {
            d[i][0] = d[i-1][0] + d[i-1][1];
            d[i][1] = d[i-1][0];
        }
        System.out.print(d[n][0] + d[n][1]);
    }
}
