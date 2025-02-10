import java.io.*;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int gcd = GCD(a,b);
        int lcm = LCM(a,b,gcd);

        System.out.println(gcd);
        System.out.print(lcm);

    }

    static int GCD(int a, int b)
    {
        while (b > 0)
        {
            int r = a % b;

            a = b;
            b = r;
        }

        return a;
    }

    static int LCM(int a, int b, int gcd)
    {
        return a * b / gcd;
    }
}
