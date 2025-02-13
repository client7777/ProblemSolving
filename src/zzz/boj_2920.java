package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2920
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[8];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<8; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        String ans = "";

        for(int i=0; i<7; i++)
        {
            if(arr[i] == arr[i+1] -1) ans = "ascending";
            else if(arr[i] == arr[i+1] + 1) ans = "descending";
            else
            {
                ans = "mixed";
                break;
            }
        }
        System.out.print(ans);
    }
}
