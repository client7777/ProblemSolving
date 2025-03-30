package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_28454
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] curDate = br.readLine().split("-");
        int year = Integer.parseInt(curDate[0]);
        int month = Integer.parseInt(curDate[1]);
        int day = Integer.parseInt(curDate[2]);

        int ans = 0;

        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++)
        {
            String[] dueDate = br.readLine().split("-");
            int giftYear = Integer.parseInt(dueDate[0]);
            int giftMonth= Integer.parseInt(dueDate[1]);
            int giftDay = Integer.parseInt(dueDate[2]);

            if(giftYear > year) ans++;
            else if(giftYear == year)
            {
                if(giftMonth > month) ans++;
                else if(giftMonth == month)
                {
                    if(giftDay >= day) ans++;
                }
            }
        }

        System.out.print(ans);
    }
}
