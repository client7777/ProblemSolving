import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.comparingInt(o->-o));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(o->o));

        for(int i=0; i<n; i++)
        {
            int num = Integer.parseInt(br.readLine());
            if(maxHeap.size() == minHeap.size()) maxHeap.add(num);
            else minHeap.add(num);

            if(!maxHeap.isEmpty() && !minHeap.isEmpty())
            {
                if(maxHeap.peek() > minHeap.peek())
                {
                    int tmp = maxHeap.poll();
                    maxHeap.add(minHeap.poll());
                    minHeap.add(tmp);
                }
            }
            sb.append(maxHeap.peek()).append('\n');
        }

        System.out.print(sb);
    }
}