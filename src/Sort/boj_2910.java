package Sort;

import java.io.*;
import java.util.*;

public class boj_2910
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> map = new LinkedHashMap<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            int num = Integer.parseInt(st.nextToken());
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> arr = new ArrayList<>(map.keySet());

        arr.sort(((o1, o2) ->
                Integer.compare(map.get(o2), map.get(o1))));

        StringBuilder sb = new StringBuilder();
        for(int i:arr)
        {
            int cnt = map.get(i);
            for(int j=0; j<cnt; j++)
            {
                sb.append(i).append(" ");
            }
        }
        System.out.print(sb);
    }
}