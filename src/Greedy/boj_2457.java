package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2457
{

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<int[]> flower = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());
            int start = startMonth * 100 + startDay;
            int end = endMonth * 100 + endDay;
            flower.add(new int[]{start, end});
        }

        //현재 시점에서 가장 늦게 지는 꽃을 선택하는 방식
        //피는 날짜가 겹치는 경우 최대한 늦게 지는 꽃을 선택해서 꽃의 수를 최소화
        Collections.sort(flower, new Comparator<int[]>()
        {
            @Override
            public int compare(int[] o1, int[] o2)
            {
                if(o1[0] == o2[0]) // 피는 날짜가 같다면
                {
                    return o2[1] - o1[1]; // 지는 날짜가 늦은 순으로 정렬 (내림차순)
                }
                return o1[0] - o2[0]; // 피는 날짜가 빠른 순으로 정렬 (오름차순)
            }
        });
        int startDay = 301;
        int endDay = 1201;
        int cnt = 0;
        int max = 0;
        int idx = 0;
        while (endDay > startDay)
        {
            boolean isFind = false;
            for(int i=idx; i<n; i++)
            {
                if(flower.get(i)[0] > startDay)
                {
                    break;
                }
                if(max < flower.get(i)[1])
                {
                    isFind = true;
                    max = flower.get(i)[1];
                    idx = i + 1;
                }
            }
            if(isFind)
            {
                startDay = max;
                cnt++;
            }
            else
                break;
        }
        if(max < endDay)
        {
            System.out.print(0);
        }
        else
            System.out.print(cnt);
    }
}
