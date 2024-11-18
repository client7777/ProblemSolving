package zzz;

import java.io.*;
import java.util.Arrays;

public class boj_2309
{

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[9];

        int sum = 0;
        for(int i=0; i<9; i++)
        {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }
        Arrays.sort(arr);

        int a = 0,b = 0;
        for(int i=0; i<8; i++)
        {
            for(int j=i+1; j<9; j++)
            {
                if(sum - (arr[i] + arr[j]) == 100)
                {
                    a = i;
                    b = j;
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<9; i++)
        {
            if(i != a && i != b)
            {
                sb.append(arr[i]).append('\n');
            }
        }
        System.out.print(sb);
    }
}
