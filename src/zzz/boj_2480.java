package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_2480
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int result = 0;
        for(int i=0; i<3; i++)
        {
            int num = Integer.parseInt(st.nextToken());
            map.put(num, map.getOrDefault(num,0 ) + 1);
            max = Math.max(max, num);
        }

        for(int key:map.keySet())
        {
            int cnt = map.get(key);
            if(cnt == 3)
            {
                result = 10000 + key * 1000;
            }
            else if(cnt == 2)
            {
                result = 1000 + key * 100;
            }
        }

        System.out.print(result == 0 ? max * 100 : result);
    }
}
