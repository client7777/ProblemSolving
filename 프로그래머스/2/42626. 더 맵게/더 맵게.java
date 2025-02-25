import java.util.*;

class Solution 
{
    public int solution(int[] scoville, int K)
    {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o->o));
        for(int val:scoville)
        {
            pq.add(val);
        }

        while (!pq.isEmpty())
        {
            if(pq.peek() >= K || pq.size() == 1) break;

            int pre = pq.poll();
            int post = pq.poll();

            pq.add(pre + post * 2);
            answer++;
        }
        
        //모든 음식의 스코빌 지수를 k 이상으로 만들지 못하면 -1 리턴
        return pq.poll() >= K ? answer : -1;
    }
}