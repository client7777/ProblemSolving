import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    static int cnt1 = 0, cnt2 = 0;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        fib1(n);
        fib2(n);

        System.out.print(cnt1 + 1 + " " + cnt2);

    }

    static int fib1(int n)
    {
        if(n == 1 || n == 2) return 1;

        cnt1++;
        return (fib1(n-1) + fib1(n-2));
    }

    static void fib2(int n)
    {
        int[] d = new int[n+1];
        d[1] = 1;
        d[2] = 1;

        for(int i=3; i<=n; i++)
        {
            d[i] = d[i-1] + d[i-2];
            cnt2++;
        }
    }
}
