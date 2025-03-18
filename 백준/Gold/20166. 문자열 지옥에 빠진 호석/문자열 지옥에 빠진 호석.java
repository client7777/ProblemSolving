import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static int n,m;
    static String[][] str;
    static int[] dx = {-1,0,1,0,-1,-1,1,1};
    static int[] dy = {0,1,0,-1,-1,1,-1,1};
    static HashMap<String, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        str = new String[n][m];
        for(int i=0; i<n; i++)
        {
            String[] row = br.readLine().split("");
            str[i] = row;
        }

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                bfs(i,j);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<k; i++)
        {
            String keyString = br.readLine();
            sb.append(map.getOrDefault(keyString, 0)).append('\n');
        }

        System.out.print(sb);
    }

    static void bfs(int startX, int startY)
    {
        map.put(str[startX][startY], map.getOrDefault(str[startX][startY],0) + 1);

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY, str[startX][startY]));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            String curStr = cur.str;

            for(int dir=0; dir<8; dir++)
            {
                int nX = (curX + dx[dir] + n) % n;
                int nY = (curY + dy[dir] + m) % m;
                String nStr = curStr + str[nX][nY];

                // 신이 좋아하는 문자열의 최대 길이는 5
                // 최대 길이를 초과하면 탐색 중지
                if(nStr.length() > 5) continue;

                q.add(new Node(nX,nY,nStr));
                map.put(nStr, map.getOrDefault(nStr, 0) + 1 );
            }
        }
    }

    static class Node
    {
        int x;
        int y;
        String str;

        public Node(int x, int y, String str)
        {
            this.x = x;
            this.y = y;
            this.str = str;
        }
    }
}
