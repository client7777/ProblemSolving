package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj_32158
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[] ch = br.readLine().toCharArray();

        ArrayList<Integer> pIndex = new ArrayList<>();
        ArrayList<Integer> cIndex = new ArrayList<>();

        for(int i=0; i<n; i++)
        {
            if(ch[i] == 'P') pIndex.add(i);
            if(ch[i] == 'C') cIndex.add(i);
        }

        int l = Math.min(pIndex.size(), cIndex.size());

        for(int i=0; i<l; i++)
        {
            ch[pIndex.get(i)] = 'C';
            ch[cIndex.get(i)] = 'P';
        }

        StringBuilder sb = new StringBuilder();
        for(char c : ch)
        {
            sb.append(c);
        }

        System.out.print(sb);
    }
}
