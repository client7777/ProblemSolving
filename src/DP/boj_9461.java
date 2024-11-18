package DP;
//파도반 수열
//정수 범위 체크
import java.io.*;
import java.util.StringTokenizer;
public class boj_9461
{
    static long[] d;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            d = new long[101];
            int test_case = Integer.parseInt(br.readLine());
            d[1] = 1; d[2] = 1; d[3] = 1;
            for(int i=4; i<=100; i++)
            {
                d[i] = d[i-3] + d[i-2];
            }
            while(test_case > 0)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                sb.append(d[n]).append("\n");
                test_case--;
            }
            System.out.print(sb.toString());
    }
}
