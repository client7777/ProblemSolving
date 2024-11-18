package BinarySearch;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1822
{
    static int n,m;
    static int[] arrA, arrB;
    static ArrayList<Integer> ans = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arrA = new int[n];
        arrB = new int[m];

        st =  new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arrA[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++)
        {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arrA);
        Arrays.sort(arrB);

        int cnt = 0;
        for(int i=0; i<n; i++)
        {
            //arrB에 대해 arrA의 원소가 B에 존재하는지 검사
            //target이 arrB에 속하지 않는다면 cnt 1만큼 누적
            int target = arrA[i];
            if(!binarySearch(target))
            {
                cnt++;
                ans.add(target);
            }
        }
        StringBuilder sb = new StringBuilder();
        if(cnt == 0)
        {
            System.out.print(cnt);
        }
        else
        {
            sb.append(cnt).append('\n');
            for(int num:ans)
            {
                sb.append(num + " ");
            }
        }
        System.out.print(sb);
    }
    static boolean binarySearch(int target)
    {
        int st = 0;
        int en = m-1;

        while (st <= en)
        {
            int mid = (st + en)/2;
            if(arrB[mid] > target)
            {
                en = mid-1;
            }
            else if(arrB[mid] <target)
            {
                st = mid + 1;
            }
            else
                return true;
        }
        return false;
    }
}
