package zzz;
// 하나의 정류장을 지날 때마다 승객의 수는 n * 2^-1
import java.io.*;

public class boj_9546
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            int k = Integer.parseInt(br.readLine());
            sb.append((1<<k)-1).append('\n');
        }
        System.out.print(sb);
    }
}
