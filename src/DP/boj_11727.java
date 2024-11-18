package DP;
//d[i] = 2*i 크기의 직사각형을 채우는 방법의 수
//좌측상단 타일은 1*2 타일로 덮이거나 2*1 타일로 덮이거나 2*2 타일로 덮여야 한다.
//2*1 타일로 덮이는 경우 d[i]의 정의로 인해서 남은 타일을 덮는 경우의 수는 d[n-1]
//1*2 타일로 덮이는 경우 위와 마찬가지로 남은 타일을 덮는 경우의 수는 d[n-2]
//2*2 타일로 덮이는 경우 1*2 타일로 덮이는 경우와 동일함
import java.io.*;
public class boj_11727
{
    static int n;
    static int[] d;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        d = new int[n+1];

        d[1] = 1;

        if(n>=2)
        {
            d[2] = 3;
        }
        for(int i=3;i<=n;i++)
        {
            d[i] = (d[i-1] + d[i-2]*2)%10007;
            // 모든 수열을 얻고나서 마지막에 나누어주는 방법보다 매 항마다 나머지 연산을 해줘서 오버플로우 방지
        }
        System.out.println(d[n]);
    }
}
