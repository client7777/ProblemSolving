package zzz;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_11866
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        LinkedList<Integer> list = new LinkedList<Integer>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++)
        {
            list.add(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        int idx = 0;
        while (list.size() > 1)
        {
            idx = (idx + (k-1)) % list.size();
            sb.append(list.remove(idx)).append(", ");
        }
        sb.append(list.remove()).append(">");
        System.out.print(sb);
    }
}
/*
public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> q = new LinkedList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++)
        {
            q.add(i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<");

        while (q.size() > 1)
        {
            for(int i=0; i<k-1; i++)
            {
                q.add(q.poll());
            }
            sb.append(q.poll()).append(", ");
        }
        sb.append(q.poll()).append(">");
        System.out.print(sb);
    }
*/
