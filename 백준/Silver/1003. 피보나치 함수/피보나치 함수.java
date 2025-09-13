import java.io.*;

public class Main
{
    static StringBuilder sb = new StringBuilder();
    static int[][] fibo = new int[42][2];
    public static void main(String[] args) throws IOException
    {
        fibo[0][0] = 1;
        fibo[1][1] = 1;
        for(int i=2; i<=40; i++)
        {
            fibo[i][0] = fibo[i-1][0] + fibo[i-2][0];
            fibo[i][1] = fibo[i-1][1] + fibo[i-2][1];
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());
        for(int i = 0; i < test_case; i++)
        {
            int n = Integer.parseInt(br.readLine());
            sb.append(fibo[n][0]).append(' ').append(fibo[n][1]).append('\n');
        }
        System.out.print(sb);
    }
}
/*
* 1.테이블 정의하기
* fibo[i][k] = 숫자 i가 k(0 or 1)를 호출한 횟수
* 2.점화식 찾기
* fibo[i][k] = fibo[i-1][k] + fibo[i-2][k]
* 3.초기값 정하기
* fibo[0][0] = 1 fibo[1][1] = 1
* */