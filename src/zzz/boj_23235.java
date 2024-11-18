package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_23235
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test_case = 1;

        while (true)
        {
            String str =  br.readLine();
            if(str.equals("0"))
            {
                break;
            }
            sb.append("Case ").append(test_case).append(": Sorting... done!").append("\n");
            test_case++;
        }
        System.out.print(sb);
    }
}
