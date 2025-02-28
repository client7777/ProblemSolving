import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] w = new int[n];
        for(int i = 0; i < n; i++)
        {
            w[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(w);
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++)
        {
            max = Math.max(max, w[i] * (n-i));
        }
        System.out.println(max);
    }
}
