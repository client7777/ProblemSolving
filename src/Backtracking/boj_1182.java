package Backtracking;
// 합이 s가 되는 부분수열의 개수
// 집합에서 부분집합의 개수는 2의 n제곱
// 각 원소는 포함되거나 포함되지 않거나
import java.io.*;
import java.util.StringTokenizer;

public class boj_1182
{
    static int n,s;
    static int[] arr;
    static int cnt = 0;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        func(0,0);
        if(s==0) cnt--; // 공집합일 경우
        System.out.print(cnt);
    }
    // 함수 내에서 i번째 수를 더할지 말지 정하고
    // i+1번째 수를 정하러 한 단계 더 들어간다는 관점
    public static void func(int cur, int tot)
    {
        if(cur == n)
        {
            if(tot == s) cnt++;
            return;
        }
        func(cur +1, tot); // 현재 원소를 포함하지 않음
        func(cur+1, tot+arr[cur]); // 현재 원소를 포함
    }
}
