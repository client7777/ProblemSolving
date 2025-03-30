import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        //입력받은 수를 내림차순 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o->-o));

        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                pq.add(Integer.parseInt(st.nextToken()));
            }
        }
        
        //정렬된 수를 n-1번 꺼냄
        for(int i=0; i<n-1; i++)
        {
            pq.poll();
        }

        //반복문에서 n-1번 꺼냈으므로 1번만 꺼내면 n번째 큰수
        System.out.print(pq.poll());

    }
}
