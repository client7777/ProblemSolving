import java.io.*;
import java.util.*;

public class Main
{
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String init = "";
        for(int i=0; i<3; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++)
            {
                init += st.nextToken();
            }
        }
        System.out.print(bfs(init));
    }

    static int bfs(String start)
    {
        Queue<String> q = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>(); // 각 상태와 그에 대한 이동횟수 저장
        map.put(start, 0);
        q.add(start);

        String ans = "123456780";

        while (!q.isEmpty())
        {
            String pos = q.poll();

            // 빈칸의 인덱스를 구해서 2차원 배열의 인덱스로 변환.
            // ex) zero의 인덱스가 8이라면 2차원배열에서는 [2][3]에 존재
            int zero = pos.indexOf('0');
            int x = zero/3;
            int y = zero%3;
            
            //현재 상태가 정답이라면 횟수 리턴
            if(pos.equals(ans)) return map.get(pos);

            for(int dir=0; dir<4; dir++)
            {
                int nX = x + dx[dir];
                int nY = y + dy[dir];
                if(OOB(nX,nY)) continue;

                //2차원 배열을 다시 1차원 문자열로 변환
                int nPos = nX * 3 + nY;
                char tmp = pos.charAt(nPos);

                //빈칸과 자리를 바꿀 문자를 t로 대체
                String next = pos.replace(tmp, 't');
                //0을 자리를 바꿀 문자로 대체
                next = next.replace('0', tmp);
                //t를 0으로 대체
                next = next.replace('t','0');

                if(!map.containsKey(next))
                {
                    q.add(next);
                    map.put(next, map.get(pos) + 1);
                }
            }
        }
        return -1;
    }

    static boolean OOB(int x, int y)
    {
        return x < 0 || y < 0 || x >= 3 || y >= 3;
    }
}
