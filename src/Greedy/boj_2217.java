package Greedy;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class boj_2217
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] w = new int[n];

        for(int i = 0; i < n; i++)
        {
            w[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(w);
        //약한 로프부터 강한 로프 순서로 정렬

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++)
        {
            max = Math.max(max, w[i] * (n-i));
            //w[i]는 해당 로프의 중량
            //(n-1)은 해당 로프를 포함한 남은 로프의 개수
            //가장 약한 로프부터 가장 강한 로프까지 가능한 모든 조합을 조사
            //i번째 로프를 포함하여 n개의 로프를 모두 사용하는 경우 고려
        }
        System.out.println(max);
    }
}
