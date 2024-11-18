package Backtracking;
//N퀸
import java.io.*;

public class boj_9663
{
    static boolean[] isUsed1;
    static boolean[] isUsed2;
    static boolean[] isUsed3;
    static int cnt = 0;
    static int n;
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        isUsed1 = new boolean[n];
        //각 열에 퀸이 존재하는지 추적하는 배열, n개의 열이 있으므로 크기가 n
        isUsed2 = new boolean[2*n-1];
        // 좌측 하단에서 우측 상단으로 이어지는 대각선에 퀸이 있는지 추적하는 배열
        isUsed3 = new boolean[2*n-1];
        // 우측 하단에서 좌측 상단으로 이어지는 대각선에 퀸이 있는지 추적하는 배열
        
        func(0);
        System.out.print(cnt);
    }
    static void func(int cur)
    {
        //n개의 모든 퀸을 배치하면 경우의 수를 추가하고 함수 종료
        if(cur == n)
        {
            cnt++;
            return;
        }
        for(int i=0; i<n; i++)
        {
            if(isUsed1[i] || isUsed2[cur + i] || isUsed3[cur - i + n -1]) continue;
            isUsed1[i] = true;
            isUsed2[i+cur] = true;
            isUsed3[cur-i+n-1] = true;
            func(cur+1);
            //재귀 탐색이 끝나고 나면 다시 false처리
            isUsed1[i] = false;
            isUsed2[i+cur] = false;
            isUsed3[cur-i+n-1] = false;
        }
    }
}
//왜 같은 행은 체크하지 않는가?
//func(int cur)는 각 행 cur에 퀸을 배치하려고 합니다.
//cur가 0에서 n-1까지 증가하므로, 각 호출에서 퀸을 배치할 행은 항상 다릅니다.
//따라서 한 행에 두 퀸이 배치될 가능성은 없습니다. 이는 함수 호출이 행 단위로 진행되기 때문에 자연스럽게 보장됩니다.
