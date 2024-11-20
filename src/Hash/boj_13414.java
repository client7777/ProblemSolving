package Hash;

import java.io.*;
import java.util.*;

public class boj_13414
{
    static int k,l;
    static LinkedHashSet<String> set = new LinkedHashSet<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken()); // 수강 가능 인원
        l = Integer.parseInt(st.nextToken()); // 대기목록의 길이

        for(int i=0; i<l; i++)
        {
            String num = br.readLine();
            if(set.contains(num))
                set.remove(num);
            set.add(num);
        }
        int idx = 0;
        for(String student:set) //List를 사용하지 않고 set을 그대로 순회
        {
            if(idx == k) break;
            System.out.println(student);
            idx++;
        }
    }
}
// 삽입 순서가 중요한 상황 -> LinkedHashSet 삽입 순서가 중요하지 않은 상황 -> HashSet
