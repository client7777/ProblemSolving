package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2224
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[][] graph = new boolean[52][52];

        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            char pre = st.nextToken().charAt(0);
            st.nextToken();
            char post = st.nextToken().charAt(0);

            int preIdx = charToInt(pre);
            int postIdx = charToInt(post);

            graph[preIdx][postIdx] = true;
        }

        for(int k=0; k<52; k++)
        {
            for(int i=0; i<52; i++)
            {
                for(int j=0; j<52; j++)
                {
                    if(i == j || graph[i][j]) continue;
                    if(graph[i][k] && graph[k][j]) graph[i][j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;

        for(int i=0; i<52; i++)
        {
            for(int j=0; j<52; j++)
            {
                if(i == j) continue; // 전건, 후건이 같은 경우는 무시

                if(graph[i][j])
                {
                    cnt++;
                    sb.append(IntToChar(i)).append(" => ").append(IntToChar(j)).append('\n');
                }
            }
        }

        System.out.println(cnt);
        System.out.print(sb);
    }
    static int charToInt(char c)
    {
        return c >= 'A' && c <= 'Z' ? c - 'A' : c - 'a' + 26;
    }

    static char IntToChar(int idx)
    {
        return (idx < 26) ? (char)(idx + 'A') : (char)(idx - 26 + 'a');
    }
}
