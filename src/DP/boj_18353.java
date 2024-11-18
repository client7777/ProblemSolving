package DP;
//병사 배치하기
//가장 긴 감소하는 부분 수열의 길이를 x라고 했을 때
//제외해야 할 병사의 수 = 전체 병사 - x
//배열을 반전 시켜서 가장 긴 감소하는 부분 수열을 구하는 방법, 직접 가장 긴 감소하는 부분 수열을 구하는 방법
import java.io.*;
import java.util.*;

public class boj_18353
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
        int[] d = new int[n];
        Arrays.fill(d,1);

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
        System.out.print(n - max);
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
        int[] d = new int[n];
        Arrays.fill(d,1);
        Collections.reverse(v);

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
        System.out.print(n - max);
*/
