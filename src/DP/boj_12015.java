package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_12015
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(arr[0]);

        for(int i=1; i<n; i++)
        {
            if(list.get(list.size() - 1) < arr[i])
            {
                list.add(arr[i]);
            }
            else
            {
                int idx = binary_search(list, arr[i]);
                list.set(idx, arr[i]);
            }
        }

        System.out.print(list.size());
    }

    static int binary_search(ArrayList<Integer> list, int target)
    {
        int left = 0;
        int right = list.size()-1;

        while(left < right)
        {
            int mid = (left + right) / 2;

            if(list.get(mid) < target)
            {
                left = mid + 1;
            }
            else
                right = mid;
        }
        return right;
    }
}
