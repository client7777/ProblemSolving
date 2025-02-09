import java.io.*;
import java.util.*;

public class Main
{
    static int n,k;
    static String startNode = "", endNode = "";
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            startNode += st.nextToken();
        }

        for(int i=1; i<=n; i++)
        {
            endNode += String.valueOf(i);
        }

        bfs();
    }
    
    static void bfs()
    {
        Set<String> visit = new HashSet<>();
        visit.add(startNode);
        
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startNode, 0));
        
        while (!q.isEmpty())
        {
            Node cur = q.poll();
            String curNode = cur.str;
            int curCnt = cur.cnt;
            
            if(curNode.equals(endNode)) // 목적지 노드에 도착하면 횟수를 출력하고 함수 종료
            {
                System.out.print(curCnt);
                return;
            }

            for(int i=0; i <= (n-k); i++)
            {
                char[] ch = curNode.toCharArray();

                for(int j=0; j<(k/2); j++)
                {
                    char tmp = ch[i+j];
                    ch[i+j] = ch[i+k-1-j];
                    ch[i+k-1-j] = tmp;
                }

                String nextNode = new String(ch);

                if(!visit.contains(nextNode))
                {
                    visit.add(nextNode);
                    q.add(new Node(nextNode, curCnt + 1));
                }
            }
        }
        System.out.print(-1);
    }
    static class Node
    {
        String str;
        int cnt;

        public Node(String str, int cnt)
        {
            this.str = str;
            this.cnt = cnt;
        }
    }
}
