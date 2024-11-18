package zzz;

import java.io.*;
import java.util.*;

public class boj_5635
{
    static HashMap<String, String> map = new HashMap<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            // 년, 월, 일을 하나의 문자열로 결합
            String info = String.format("%04d%02d%02d", year, month, day);
            map.put(name, info);
        }
        ArrayList<Map.Entry<String,String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        System.out.println(list.get(map.size()-1).getKey());
        System.out.println(list.get(0).getKey());
    }
}