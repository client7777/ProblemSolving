package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class boj_9372
{
    static int test_case;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test_case = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            //모든 간선의 가중치가 같으므로 최소 신장 트리를 만들기 위해서는 어떤 간선을 선택하든 전체 노드 - 1개의 간선만 선택하면 된다.
            for(int i=0; i<m; i++)
            {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

            }
            // n-1개의 간선으로 모든 노드를 연결할 수 있기 테스트케이스마다 n-1만 출력
            sb.append(n-1).append('\n');  
        }
        System.out.print(sb);
    }

}
