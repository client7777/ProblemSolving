package zzz;

import java.io.*;
import java.util.ArrayList;

public class boj_29808
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int tmp = n / 4763;

        if(n % 4763 != 0)
        {
            System.out.print(0);
            return;
        }

        ArrayList<int[]> arr = new ArrayList<>();
        for(int i=0; i<=200; i++)
        {
            for(int j=0; j<=200; j++)
            {
                if(tmp == i * 508 + j * 212 || tmp == i * 508 + j * 305
                        || tmp == i * 108 + j * 212 || tmp == i * 108 + j * 305)
                {
                    arr.add(new int[]{i,j});
                }
            }
        }
        System.out.println(arr.size());
        for(int[] pair:arr)
        {
            System.out.println(pair[0] + " " + pair[1]);
        }
    }
}
