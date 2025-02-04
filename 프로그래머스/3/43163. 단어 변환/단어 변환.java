import java.util.*;

class Solution 
{
    public int solution(String begin, String target, String[] words)
    {
        int answer = 0;

        boolean[] visit = new boolean[words.length];

        Queue<Edge> q = new LinkedList<>();
        q.add(new Edge(begin, 0));

        while (!q.isEmpty())
        {
            Edge cur = q.poll();
            String curString = cur.str;
            int curCnt = cur.cnt;

            if(curString.equals(target))
            {
                answer = curCnt;
                break;
            }

            for(int i=0; i<words.length; i++)
            {
                int tmp = 0;

                char[] curNode = curString.toCharArray();
                char[] nextNode = words[i].toCharArray();

                for(int j=0; j<curNode.length; j++)
                {
                    if(curNode[j] == nextNode[j]) tmp++;
                }

                if(tmp == curNode.length - 1 && !visit[i])
                {
                    q.add(new Edge(words[i], curCnt + 1));
                    visit[i] = true;
                }
            }
        }

        return answer;
    }

    static class Edge
    {
        String str;
        int cnt;

        public Edge(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }
    }
}