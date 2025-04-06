import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken()); // 보석의 개수
        int k = Integer.parseInt(st.nextToken()); // 가방의 개수

        ArrayList<int[]> list = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.add(new int[]{m,v});
        }
        list.sort(Comparator.comparingInt(o -> o[0])); // 보석의 무게를 기준으로 오름차순 정렬
        int[] bag = new int[k];
        for(int i=0; i<k; i++)
        {
            bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long sum = 0;
        int idx = 0;

        for(int b:bag)
        {
            while (idx < n && list.get(idx)[0] <= b)
            {
                pq.add(list.get(idx)[1]);
                idx++;
            }
            if(!pq.isEmpty())
            {
                sum += pq.poll();
            }
        }
        System.out.print(sum);
    }
}
//가격이 높은 보석부터 확인하며 보석을 담을 수 있는 가방중 무게가 가장 작은 가방을 이용해 보석을 담는게 이득이다.
