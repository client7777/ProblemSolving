package zzz;
import java.io.*;
public class boj_30642
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int k = Integer.parseInt(br.readLine());

        int ans = 0;

        if(str.equals("annyong") && k % 2 != 0)
        {
            ans = k;
        }
        else if(str.equals("annyong") && k % 2 == 0)
        {
            if(k-1 == 0)
            {
                ans = k + 1;
            }
            else
                ans = k -1;
        }
        else if(str.equals("induck") && k % 2 == 0)
        {
            ans = k;
        }
        else if(str.equals("induck") && k % 2 == 1)
        {
            if(k-1 == 0)
            {
                ans = k+1;
            }
            else ans = k-1;
        }
        System.out.print(ans);
    }

}
