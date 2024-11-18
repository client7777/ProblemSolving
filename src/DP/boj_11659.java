package DP;
import java.io.*;
import java.util.StringTokenizer;
// d[i] = a[1] + a[2] + ... a[i]
// d[i] = d[i-1] + a[i]
public class boj_11659
{
    static int n,m;
    static int[] arr, d;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        d = new int[n+1];
        d[0] = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
            d[i] = d[i-1] + arr[i];
        }
        while(m>0)
        {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            sb.append(d[j] - d[i-1]).append("\n");
            m--;
        }
        System.out.print(sb.toString());
    }
}
