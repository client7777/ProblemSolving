package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_11800
{
    static String[] dice = {"Yakk", "Doh","Seh","Ghar", "Bang", "Sheesh"};
    static String[] dice2 = {"Habb Yakk","Dobara","Dousa","Dorgy","Dabash", "Dosh"};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t=1; t<=test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num1 = Integer.parseInt(st.nextToken())-1;
            int num2 = Integer.parseInt(st.nextToken())-1;

            if(num1 != num2)
            {
                if((num1 == 5 && num2 == 4) || (num1 == 4 && num2 == 5))
                {
                    sb.append("Case " + t + ": Sheesh Beesh").append('\n');
                }
                else
                {
                    if(num2 > num1)
                    {
                        int tmp = num1;
                        num1 = num2;
                        num2 = tmp;
                    }
                    sb.append("Case " + t + ": " + dice[num1] + " " + dice[num2]).append('\n');
                }
            }
            else
            {
                sb.append("Case " + t + ": " + dice2[num1]).append('\n');
            }
        }
        System.out.print(sb);
    }
}
