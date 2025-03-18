import java.io.*;
import java.util.*;

public class Main
{
    static int[] parent;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 집합의 개수
        int m = Integer.parseInt(st.nextToken()); // 연산의 개수

        parent = new int[n+1]; // n+1개의 집합이 존재
        for(int i=1; i<=n; i++)
        {
            // 각 원소는 자기 자신을 부모로 가지는 배열을 초기화
            parent[i] = i; // i = 노드 번호, parent[i] 의 값은 부모 노드의 번호, 루트 노드x
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(command == 1)
            {
                sb.append(isSameParent(x,y)?"YES":"NO").append('\n');
            }
            else if(command == 0)
            {
                union(x,y);
            }
        }
        System.out.print(sb);
    }
    //원소 x의 대표자를 찾는 함수
    static int find(int x)
    {
        if(x == parent[x]) // x가 자기 자신을 부모로 가지면 그가 대표자
        {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    static void union(int x,int y)
    {
        //두 집합을 합치기 위해 각 원소의 대표자를 찾음
        x = find(x);
        y = find(y);
        
        //대표자의 번호가 더 작은 집합에 번호가 더 큰 집합을 이어붙임
        if(x!=y)
        {
            if(x < y)
            {
                parent[y] = x;
            }
            else
                parent[x] = y;
        }
    }
    static boolean isSameParent(int x,int y)
    {
        // 두 원소의 대표자를 찾고, 같은 집합에 속해있는지 판별, 두 노드의 루트 노드가 같다면 같은 집합에 속함
        x = find(x);
        y = find(y);
        if(x == y)
            return true;
        return false;
    }
}
// 입력 0 = 합집합 연산, 입력 1 = 교집합 연산