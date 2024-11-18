package DP;
//1 ... 1 , 2...2 , 3 ... 3 각 1,2,3 사이에 넣을 숫자를 정함
import java.io.*;
import java.util.*;

public class boj_15991
{
    static int test_case;
    static final int DIVIDE = 1_000_000_009;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test_case = Integer.parseInt(br.readLine());

        //4,6에서 2+2, 3+3과 같은 특수한 상황이 발생하므로 6번째 값까지는 직접 구하고 7번째 값부터는 특수한 상황이 발생하지 않으므로
        //반복문을 통해서 주어진 범위의 값을 미리 구함
        long[] d = new long[100001];
        d[1] = 1;
        d[2] = 2;
        d[3] = 2;
        d[4] = 3;
        d[5] = 3;
        d[6] = 6;

        for(int i=7; i<d.length; i++)
        {
            d[i] = (d[i-2] + d[i-4] + d[i-6]) % DIVIDE;
        }
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            sb.append(d[n] % DIVIDE).append('\n');
        }
        System.out.print(sb);
    }
}
/*
d[1] = 1 1
d[2] = 2 1+1 2
d[3] = 2 1+1+1 3
d[4] = 3 1+1+1+1 1+2+1 2+2 2+2라는 특수항 상황이 발생할 수 있다.
d[5] = 3 1+1+1+1+1 1+3+1 2+1+2
d[6] = 6 1+1+1+1+1+1 1+1+2+1+1 1+2+2+1 2+1+1+2 2+2+2 3+3 3+3이라는 특수한 상황이 발생할 수 있다.
*/