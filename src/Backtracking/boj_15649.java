package Backtracking;
//1~n까지 자연수 중에서 중복없이 m개를 고른 수열
import java.io.*;
import java.util.*;

public class boj_15649
{
    static int[] arr; // 수열을 담을 배열
    static boolean[] visit; // 특정 수가 쓰였는지 나타내는 배열 (1 ~ N까지)
    static int n,m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m]; // 길이가 m인 배열
        visit = new boolean[n+1]; //특정 수가 사용되었는지 여부를 나타내는 배열
        dfs(0);
        System.out.println(sb);
    }
    public static void dfs(int depth)
    {
        // 탐색의 깊이가 m이 되었을 경우 m개를 모두 택했으니 수열을 모두 출력한 후 함수를 종료
        // 현재 선택된 수의 개수인 depth를 인자로 받아 호출됨
        if(depth == m)
        {
            for(int val:arr)
            {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }
        //k != m 이면
        for(int i=1; i<=n; i++)
        {
            //만약 i가 사용하지 않은 숫자라면
            if(!visit[i])
            {
                //사용되지 않은 숫자를 선택
                visit[i] = true;
                //depth에 해당 숫자를 저장
                arr[depth] = i;
                //재귀호출
                dfs(depth+1);
                //재귀 호출이 종료되면, 다시 돌아와서 선택했던 숫자의 사용을 해제하고 다음 숫자를 시도
                visit[i] = false;
            }
        }
    }
}
// 재귀 탐색: 탐색 깊이가 m이 될 때까지 재귀적으로 호출됨
// 깊이가 m이면 배열에 저장된 수열을 출력
// 그렇지 않으면 1부터 n까지 숫자를 순회하면서 사용되지 않은 숫자를 찾음.