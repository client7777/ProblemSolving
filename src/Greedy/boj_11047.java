package Greedy;
import java.io.*;
import java.util.*;
public class boj_11047
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] val = new int[n];

        for(int i = 0; i < n; i++)
        {
            val[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(val);

        int ans = 0;

        for(int i=n-1; i>=0; i--)
        {
            ans += k/val[i];
            k %= val[i];
        }
        System.out.println(ans);
    }
}
