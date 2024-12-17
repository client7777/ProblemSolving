package zzz;

import java.io.*;
import java.util.ArrayList;

public class boj_11536
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<String> arr = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            arr.add(br.readLine());
        }

        ArrayList<String> increasing = new ArrayList<>(arr);
        increasing.sort(String::compareTo);

        if(arr.equals(increasing))
        {
            System.out.print("INCREASING");
            return;
        }

        ArrayList<String> decreasing = new ArrayList<>(arr);
        decreasing.sort((a,b) -> b.compareTo(a));

        System.out.print(arr.equals(decreasing) ? "DECREASING" : "NEITHER");
    }
}
