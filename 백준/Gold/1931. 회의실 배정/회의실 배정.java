import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//지금 최선의 선택을 하지 않았을 경우 최선의 선택을 했을 경우보다 더 결과가 좋아질 수 없다. 

public class Main {

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
