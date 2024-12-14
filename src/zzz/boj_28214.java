package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_28214
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int[] arr = new int[n*k];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<arr.length; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;

        int noCream = 0;
        int idx = 0;
        for (int i : arr) {
            if (i == 0) {
                noCream++;
            }
            idx++;

            if (idx == k) {
                if (noCream < p) {
                    cnt++;
                }
                idx = 0;
                noCream = 0;
            }
        }
        System.out.print(cnt);
    }
}
