package Graph;
// 진실을 아는 사람과 같은 집합에 속한 사람이 있는 파티는 과장된 말을 할 수 없다.
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1043
{
    static int n,m;
    static int[] parent;
    static ArrayList<Integer>[] party;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 사람 수
        m = Integer.parseInt(st.nextToken()); // 파티 수

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수
        int[] fact = new int[k]; // k = 0 이면 크기가 0인 배열이 생성됨
        //반복문은 첫 조건부터 거짓이기 때문에 실행되지 않고 다음 코드로 넘어감
        for(int i=0; i<k; i++)
        {
            fact[i] = Integer.parseInt(st.nextToken());
        }
        
        // 루트 노드 초기화
        parent = new int[n+1];
        for(int i=1; i<=n; i++)
        {
            parent[i] = i;
        }

        party = new ArrayList[m+1];
        for(int i=1; i<=m; i++)
        {
            party[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int partySize = Integer.parseInt(st.nextToken());
            for(int j=0; j<partySize; j++)
            {
                party[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i=1; i<=m; i++)
        {
            int firstMan = party[i].get(0);
            for(int j=1; j<party[i].size(); j++)
            {
                union(firstMan, party[i].get(j)); // i번째 파티에 참석하는 사람들을 같은 집합으로 묶음
            }
        }
        int cnt = 0;
        for(int i=1; i<=m; i++)
        {
            boolean flag = true;
            int leader = party[i].get(0); // 각 집합의 대표자를 뽑음
            for(int j=0; j<k; j++)
            {
                // 각 집합의 대표자가 진실을 아는 사람과 같은 집합에 속했다면
                if(find(leader) == find(fact[j]))
                {
                    flag = false;
                    break;
                }
            }
            if(flag) cnt++;
        }
        System.out.print(cnt);
    }
    static int find(int x)
    {
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int x,int y)
    {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY)
        {
            parent[rootY] = rootX;
        }
    }
}
