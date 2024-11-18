package DP;
// n이 음수일 때 dp의 값은 n이 양수일 때와 절댓값이 같고 -n이 짝수일 때 dp 값은 음수를 갖음
import java.io.*;

public class boj_1788
{
    static final int MOD = 1_000_000_000;
    static final int MAX = 1000000;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if(n == 0)
        {
            System.out.println(0);
            System.out.println(0);
            return;
        }

        StringBuilder sb = new StringBuilder();
        int[] d = new int[MAX+1];
        d[0] = 0;
        d[1] = 1;
        for(int i=2; i<=MAX; i++)
        {
            d[i] = (d[i-1] + d[i-2]) % MOD;
        }
        if(n < 0)
        {
            if(n%2 == 0) // n이 짝수일 경우 함수의 값은 음수
            {
                sb.append(-1).append('\n');
            }
            else
            {
                sb.append(1).append('\n');
            }
        }
        else
            sb.append(1).append('\n');

        long ans = d[Math.abs(n)] % MOD;
        sb.append(ans).append('\n');
        System.out.print(sb);
    }
}
