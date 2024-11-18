package zzz;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_2960
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] isPrime = new boolean[n+1];
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=2; i<=n; i++)
        {
            for(int j=i; j<=n; j+=i)
            {
                if(!isPrime[j])
                {
                    isPrime[j] = true;
                    ans.add(j);
                }
            }
        }
        System.out.print(ans.get(k-1));
    }
}
