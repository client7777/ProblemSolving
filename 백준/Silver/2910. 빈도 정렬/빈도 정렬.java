import java.io.*;
import java.util.*;

public class Main 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); 
        int c = Integer.parseInt(st.nextToken());
        
        List<Integer> list = new ArrayList<>(); 
       
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> original = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) 
        {
            list.add(Integer.parseInt(st.nextToken()));
            original.add(list.get(i));
            map.put(list.get(i), map.getOrDefault(list.get(i), 0) + 1);
        }

        // 빈도수를 기준으로 리스트를 정렬
        Collections.sort(list, (o1, o2) -> {
            if (map.get(o1) == map.get(o2)) 
            {
               return original.indexOf(o1) - original.indexOf(o2);
            } 
            else 
            {     
                return Integer.compare(map.get(o2), map.get(o1));
            }
        });

        // 정렬된 리스트를 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) 
        {
            sb.append(list.get(i) + " ");
        }
        System.out.println(sb);
    }
}