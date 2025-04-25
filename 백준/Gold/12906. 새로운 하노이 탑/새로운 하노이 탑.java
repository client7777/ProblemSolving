import java.io.*;
import java.util.*;

public class Main
{
    static String[] str = new String[3];
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        for(int i=0; i<3; i++)
        {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            if(cnt == 0) str[i] = "";
            else str[i] = st.nextToken();
        }

        System.out.print(bfs());
    }

    static int bfs()
    {
        HashSet<String> visit = new HashSet<>();
        visit.add(toString(str));

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(str, 0));

        while (!q.isEmpty())
        {
            Node cur = q.poll();

            if(isAnswer(cur.state)) return cur.cnt;

            for(int i=0; i<3; i++)
            {
                if(cur.state[i].isEmpty()) continue; // 원판이 없으면 옮기는 연산 불가능

                for(int j=0; j<3; j++)
                {
                    if(i == j) continue;

                    String[] nextState = cur.state.clone();

                    char movingDisk = nextState[i].charAt(nextState[i].length() -1);
                    nextState[i] = nextState[i].substring(0, nextState[i].length() -1);
                    nextState[j] += movingDisk;

                    String key = toString(nextState);

                    if(!visit.contains(key))
                    {
                        visit.add(key);
                        q.add(new Node(nextState, cur.cnt + 1));
                    }
                }
            }
        }

        return -1;
    }

    static boolean isAnswer(String[] state)
    {
        for(int i=0; i<3; i++)
        {
            if(state[i].isEmpty()) continue;

            for(char c : state[i].toCharArray())
            {
                if(c != i + 'A') return false;
            }
        }

        return true;
    }

    static String toString(String[] state)
    {
        return state[0] + "|" + state[1] + "|" + state[2];
    }

    static class Node
    {
        String[] state;
        int cnt;

        public Node(String[] state, int cnt)
        {
            this.state = new String[3];
            //외부에서 값(state 배열)을 받아서, 내부에서는 그 값만 복사하고 완전히 독립된 공간에 저장한다.
            System.arraycopy(state, 0, this.state, 0, 3);

            this.cnt = cnt;
        }
    }
}
