import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());

        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=n; i++)
        {
            q.add(i);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty())
        {
            sb.append(q.poll()).append(" "); // 맨 위에 있는 카드를 버림
            q.add(q.peek()); // 그 다음 순서 카드를 마지막 순서로 보냄
            q.poll();
        }

        System.out.print(sb);
    }
}
