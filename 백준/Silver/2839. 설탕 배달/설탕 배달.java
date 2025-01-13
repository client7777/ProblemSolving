import java.io.*;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] d = new int[5001];

        Arrays.fill(d, 5001);

        if(n >= 3) d[3] = 1;
        if(n >= 5) d[5] = 1;

        for(int i=6; i<=n; i++)
        {
            if(d[i-3] != 5001 || d[i-5] != 5001)
            {
                d[i] = Math.min(d[i-3], d[i-5]) + 1;
            }
        }
        System.out.print(d[n] == 5001 ? -1 : d[n]);
    }
}
