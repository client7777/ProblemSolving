package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_15650
{
    static int n,m;
    static ArrayList<Integer> ans = new ArrayList<>();
    static boolean[] is_used;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        is_used = new boolean[n+1];

        backTracK(1,0);

        System.out.print(sb);

    }
    static void backTracK(int at, int depth)
    {
        if(depth == m)
        {
            for(int val:ans)
            {
                sb.append(val + " ");
            }
            sb.append('\n');
            return;
        }
        for(int i=at; i<=n; i++)
        {
            if(!is_used[i])
            {
                ans.add(i);
                is_used[i] = true;
                backTracK(i+1,depth+1);
                ans.remove(ans.size()-1);
                is_used[i] = false;
            }
        }
    }
}
//depth == 0 은 첫 번째 위치에 숫자를 넣을 때 아무 제약 없이 선택할 수 있다.
//i > arr[depth - 1]는 현재 위치에 있는 숫자가 이전에 선택한 숫자보다 커야 함을 보장
/*
* public static void dfs(int at, int depth) {
        if (depth == M) {
            for (int val : arr) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = at; i <= N; i++) {
            arr[depth] = i;
            dfs(i + 1, depth + 1);
        }
    }*/

/*
for(int i=1; i<=n; i++)
        {
            if(!is_used[i] && (depth == 0 || i > ans.get(depth-1)))
            {
                ans.add(i); // 조합을 담을 리스트에 값 추가
                is_used[i] = true; // 사용 여부 표시
                backTracK(depth + 1); // 다음 값 탐색
                //상태 복원
                ans.remove(ans.size()-1);
                is_used[i] = false;
            }
        }
*/