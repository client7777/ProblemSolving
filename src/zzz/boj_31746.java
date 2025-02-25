package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_31746
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if(n % 2 == 1)
        {
            System.out.print("evoLmoCicS");
        }
        else
        {
            System.out.print("SciComLove");
        }
    }
}
