//로또
import java.io.*;
import java.util.StringTokenizer;
public class Main
{
    static int[] ans; // 고른 수를 담을 배열
    static int[] numbers; // 입력받은 숫자들을 담을 배열
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            // 한 줄의 입력을 읽어옴, 읽어온 문자열을 공백을 기준으로 분리
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 첫 번째 숫자를 읽어서 k로 변환, 집합 s의 크기
            int k = Integer.parseInt(st.nextToken());
            // 입력된 숫자가 0이면 탈출
            if(k == 0) break;
            numbers = new int[k];
            // 숫자 6개를 뽑아서 저장할 배열
            ans = new int[6];
            for(int i = 0; i < k; i++)
            {
                numbers[i] = Integer.parseInt(st.nextToken());
            }
            dfs(0,0);
            sb.append('\n'); // 테스트 케이스 한개가 끝나면 개행
        }
        System.out.print(sb.toString());
    }
    static void dfs(int start, int depth)
    {
        // 6개의 숫자를 모두 골랐다면
        if(depth == 6)
        {
            for(int val : ans)
            {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }
        for(int i = start; i < numbers.length; i++)
        {
            ans[depth] = numbers[i];
            dfs(i + 1, depth + 1);
        }
    }
}
