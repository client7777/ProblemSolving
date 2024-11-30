package zzz;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class boj_15828
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<Integer> q = new LinkedList<>();
        while (true)
        {
            int info = Integer.parseInt(br.readLine());
            if(info == -1)
                break;
            if(info == 0)
                q.poll();
            else if(q.size() < n)
                q.add(info);
        }
        StringBuilder sb = new StringBuilder();
        if(q.isEmpty())
        {
            sb.append("empty");
        }
        else
        {
            while (!q.isEmpty())
            {
                sb.append(q.poll() + " ");
            }
        }
        System.out.print(sb);
    }
}
