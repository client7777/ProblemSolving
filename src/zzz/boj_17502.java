package zzz;

import java.io.*;

public class boj_17502
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        char[] arr = str.toCharArray();
        for(int i=0; i<n/2+1; i++)
        {
            char cur = 'a';
            if(arr[i] != '?') cur = arr[i];
            if(arr[n-i-1] != '?') cur = arr[n-i-1];
            arr[i] = arr[n-i-1] = cur;
        }
        System.out.print(String.valueOf(arr));
    }
}
