package zzz;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_4641
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            int cnt = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            if(st.countTokens() == 1) break;
            int[] arr = new int[st.countTokens()-1];
            for(int i=0; i<arr.length; i++)
            {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);
            for(int i=0; i<arr.length-1; i++)
            {
                for(int j=1; j<arr.length; j++)
                {
                    if(arr[i]*2 == arr[j])
                    {
                        cnt++;
                        break;
                    }
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.print(sb);
    }
}
