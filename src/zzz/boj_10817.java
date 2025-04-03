package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_10817
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0; i<3; i++)
        {
            list.add(Integer.parseInt(st.nextToken()));
        }

        list.sort((o1,o2) -> o2-o1);

        list.remove(0);

        System.out.print(list.get(0));
    }
}
