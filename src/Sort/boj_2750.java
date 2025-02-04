package Sort;
//수 정렬하기

import java.io.*;
import java.util.*;

public class boj_2750 
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            int num = Integer.parseInt(br.readLine());
            arr.add(num);
        }

        arr.sort(Comparator.comparingInt(o->o));

        for(int val:arr)
        {
            System.out.println(val);
        }
    }
}
