import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int testCaseCount = sc.nextInt();

        while (testCaseCount-- > 0)
        {
            
            int N = sc.nextInt();  
            int M = sc.nextInt();

            
            Queue<int[]> queue = new LinkedList<>();
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

            for (int i = 0; i < N; i++) 
            {
                int importance = sc.nextInt();
                queue.add(new int[]{i, importance}); 
                priorityQueue.add(importance);
            }

            int printOrder = 0;

            while (!queue.isEmpty()) 
            {
                int[] current = queue.poll();
                int currentDocIndex = current[0];
                int currentDocImportance = current[1];
                
                if (currentDocImportance == priorityQueue.peek()) 
                {
                    printOrder++;
                    priorityQueue.poll();
                    
                    if (currentDocIndex == M) 
                    {
                        System.out.println(printOrder);
                        break;
                    }
                } else 
                {
                    queue.add(current);
                }
            }
        }

        sc.close();
    }
}
