package DP;
//테이블 정의하기: arr[i] = i를 1로 만들기 위해 필요한 연산 사용 횟수의 최솟값
//점화식 찾기: 3으로 나누어지면 나누거나, 2로 나누어지면 나누거나, 1을 빼거나
//초기값 정의하기: arr[1] = 0
import java.io.*;
public class boj_1463
{
    static int[] arr;
    static int n;
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        arr[1] = 0;
        for(int i=2; i<=n; i++)
        {
            arr[i] = arr[i-1] + 1;
            if(i%3==0) arr[i] = Math.min(arr[i], arr[i/3] + 1);
            if(i%2==0) arr[i] = Math.min(arr[i], arr[i/2] + 1);
        }
        System.out.print(arr[n]);
    }
}
