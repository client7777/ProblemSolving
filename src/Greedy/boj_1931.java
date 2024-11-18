package Greedy;
import java.io.*;
import java.util.*;
public class boj_1931
{
    public static void main(String[] args)throws IOException {

        // 회의가 가장 빨리 끝나는 시간을 골라라

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] time = new int[n][2];
        //0은 시작시간, 1은 종료시간

        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            time[i][0] = Integer.parseInt(st.nextToken()); // 시작시간
            time[i][1] = Integer.parseInt(st.nextToken()); // 종료시간

        }
        //o1[0] - o2[0] 첫번째 숫자 기준 오름차순
        //o2[0] - o1[0] 첫번째 숫자 기준 내림차순
        //o1[1] - o2[1] 두번째 숫자 기준 오름차순
        //o2[1] - o1[1] 두번째 숫자 기준 내림차순

        //종료시간 오름차순 정렬
        //종료시간이 같다면 시작하는 시간이 빠른 순서로 정렬
        Arrays.sort(time, new Comparator<int[]>() {

            public int compare(int[] o1, int[] o2)
            {
                if(o1[1] == o2[1])
                {
                    return o1[0] - o2[0];
                }

                return o1[1] - o2[1];
            }
        });
        int cnt = 0;
        int prev_end_time = 0;

        for(int i=0; i<n; i++)
        {
            if(prev_end_time <= time[i][0])
            {
                prev_end_time = time[i][1];
                cnt++;
            }
        }
        System.out.print(cnt);
    }
}
