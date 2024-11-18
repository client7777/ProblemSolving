package Backtracking;
//DFS & BackTracking
import java.util.*;
import java.io.*;

public class boj_12869

{   static int[] scv;
    static boolean[][][] visit = new boolean[61][61][61];
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        scv = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            scv[i] = Integer.parseInt(st.nextToken());
        }
        //scv 빈자리는 0으로 초기화
        for(int i=n; i<3; i++)
        {
            scv[i] = 0;
        }
        int s1 = scv[0], s2 = scv[1], s3 = scv[2];
        findMin(s1,s2,s3,0);
        System.out.print(ans);
    }
    static void findMin(int a, int b, int c, int cnt)
    {
        //음수일 경우 0으로 만듦
        a = Math.max(0 ,a);
        b = Math.max(0, b);
        c = Math.max(0, c);

        if(a == 0 && b == 0 && c == 0)
        {
            ans = Math.min(ans, cnt);
        }
        //가장 체력이 많이 남은 scv부터 공격해서 체력을 줄여나감
        Integer[] sorting = {a,b,c,};
        Arrays.sort(sorting, Comparator.reverseOrder());
        a = sorting[0];
        b = sorting[1];
        c = sorting[2];

        //이미 탐색한 체력 조합에 대해서는 탐색 중지
        if(visit[a][b][c]) return;
        else visit[a][b][c] = true;

        //현재 탐색중인 조합의 횟수가 최솟값보다 크다면 탐색 중지
        if(ans < cnt) return;

        // 공격을 할 수 있는 조합 = 3!
        findMin(a - 9, b - 3, c - 1, cnt + 1);
        findMin(a - 9, b - 1, c - 3, cnt + 1);
        findMin(a - 3, b - 9, c - 1, cnt + 1);
        findMin(a - 3, b - 1, c - 9, cnt + 1);
        findMin(a - 1, b - 3, c - 9, cnt + 1);
        findMin(a - 1, b - 9, c - 3, cnt + 1);
        
    }
}
