package zzz;

import java.io.*;
import java.util.*;

public class boj_15720
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        ArrayList<Integer> burger = new ArrayList<>();
        ArrayList<Integer> side = new ArrayList<>();
        ArrayList<Integer> liquor = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<a; i++)
        {
            burger.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<b; i++)
        {
            side.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<c; i++)
        {
            liquor.add(Integer.parseInt(st.nextToken()));
        }

        burger.sort(Comparator.comparingInt(o->-o));
        side.sort(Comparator.comparingInt(o->-o));
        liquor.sort(Comparator.comparingInt(o->-o));

        int max = 0;
        int dis = 0;

        while (true)
        {
            if(burger.isEmpty() && side.isEmpty() && liquor.isEmpty()) break;
            int cnt = 0;
            int sum = 0;

            if(!burger.isEmpty())
            {
                max += burger.get(0);
                sum += burger.get(0);
                cnt++;
                burger.remove(0);
            }
            if(!side.isEmpty())
            {
                max += side.get(0);
                sum += side.get(0);
                cnt++;
                side.remove(0);
            }
            if(!liquor.isEmpty())
            {
                max += liquor.get(0);
                sum += liquor.get(0);
                cnt++;
                liquor.remove(0);
            }
            if(cnt == 3)
            {
                sum = (int)(sum * 0.9);
            }
            dis += sum;
        }
        System.out.println(max);
        System.out.println(dis);
    }
}
