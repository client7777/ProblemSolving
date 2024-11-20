package Hash;

import java.io.*;
import java.util.*;

public class boj_7785
{
    static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            String log = st.nextToken();

            if(log.equals("enter"))
                set.add(name); // 출근한 기록이 있으면 set에 추가
            else
                set.remove(name); // 퇴근한 기록이 있으면  set에서 제거
        }
        ArrayList<String> ans = new ArrayList<>(set); // set을 ArrayList로 변환
        Collections.sort(ans, Collections.reverseOrder());
        //ans.sort(Comparator.reverseOrder());
        //ans.sort(((o1, o2) -> o2.compareTo(o1)));
        for(String name:ans)
        {
            System.out.println(name);
        }
    }
}
