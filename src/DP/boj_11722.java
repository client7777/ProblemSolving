package DP;
//가장 긴 감소하는 부분 수열
//직접 가장 긴 감소하는 부분 수열을 구하는 방법, 간접적으로 구하는 방법
import java.io.*;
import java.util.*;

public class boj_11722
{
    static int n;
    static ArrayList<Integer> v = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            v.add(Integer.parseInt(st.nextToken()));
        }
        int[] d = new int[n]; // d[i] = i번째 수를 마지막 원소로 갖는 부분 수열의 길이
        Arrays.fill(d, 1); // 모든 원소는 자기 자신만을 원소로 가지는 길이 1인 부분 수열을 가짐

        int max = 1;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<i; j++)
            {
                if(v.get(i) < v.get(j))
                {
                    d[i] = Math.max(d[i], d[j] + 1);
                }
            }
            max = Math.max(max, d[i]);
        }
        System.out.print(max);
    }
}
/*
static int n;
    static ArrayList<Integer> v = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            v.add(Integer.parseInt(st.nextToken()));
        }
        int[] d = new int[n]; // d[i] = i번째 수를 마지막 원소로 갖는 부분 수열의 길이
        Arrays.fill(d, 1); // 모든 원소는 자기 자신만을 원소로 가지는 길이 1인 부분 수열을 가짐
        Collections.reverse(v); // 배열을 반전시킴

        int max = 1;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<i; j++)
            {
                if(v.get(i) > v.get(j))
                {
                    d[i] = Math.max(d[i], d[j] + 1);
                }
            }
            max = Math.max(max, d[i]);
        }
        System.out.print(max);
    }
*/
