package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1991
{
    static int n;
    static int[] lc = new int[30];
    static int[] rc = new int[30];
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            char c,l,r;
            c = st.nextToken().charAt(0);
            l = st.nextToken().charAt(0);
            r = st.nextToken().charAt(0);

            if(l != '.') lc[c-'A'+1] = l-'A'+1; // 현재 노드의 왼쪽 자식 노드
            if(r != '.') rc[c-'A'+1] = r-'A'+1; // 현재 노드의 오른쪽 자식 노드
        }
        preOrder(1);
        System.out.println();
        inOrder(1);
        System.out.println();
        postOrder(1);
    }
    static void preOrder(int cur)
    {
        System.out.print((char)(cur+'A'-1));
        if(lc[cur] != 0) preOrder(lc[cur]);
        if(rc[cur] != 0) preOrder(rc[cur]);
    }
    static void inOrder(int cur)
    {
        if(lc[cur] != 0) inOrder(lc[cur]);
        System.out.print((char)(cur+'A'-1));
        if(rc[cur] != 0) inOrder(rc[cur]);
    }
    static void postOrder(int cur)
    {
        if(lc[cur] != 0) postOrder(lc[cur]);
        if(rc[cur] != 0) postOrder(rc[cur]);
        System.out.print((char)(cur+'A'-1));
    }
}
//pre -> 1.현재 정점 방문 2.왼쪽 전위 방문 3.오른쪽 전위 방문
//in -> 1.왼쪽 서브 트리를 중위 순회 2.현재 정점 방문 3.오른쪽 서브 트리를 중위 순회
//post -> 왼쪽 서브 트리를 후위 순회 2.오른쪽 서브 트리를 후위 순회 3.현재 정점을 방문
