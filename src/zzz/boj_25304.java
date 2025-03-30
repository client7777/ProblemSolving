package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_25304
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tot = Integer.parseInt(br.readLine());

        int cnt = Integer.parseInt(br.readLine());

        int amount = 0;
        StringTokenizer st;
        while (cnt -- > 0)
        {
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int ea = Integer.parseInt(st.nextToken());

            amount += (price * ea);
        }

        System.out.print(tot == amount ? "Yes" : "No");
    }
}
