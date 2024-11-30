package zzz;

import java.io.*;

public class boj_16406
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 친구가 맞힌 문제의 개수

        String me = br.readLine();
        String friend = br.readLine();

        int cnt = 0;

        for(int i=0; i<me.length(); i++)
        {
            if(me.charAt(i) == friend.charAt(i)) cnt++;
        }
        System.out.print(me.length() - Math.abs(n - cnt));
    }
}