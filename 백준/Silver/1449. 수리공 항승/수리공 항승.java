import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 물이 새는 곳의 개수
		int l = Integer.parseInt(st.nextToken()); // 테이프의 길이

		List<Integer> point = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++){
			point.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(point);

		int cnt = 0;
		double cover = 0;

		for(int val : point){
			if(val > cover){
				cnt++;
				cover = val - 0.5 + l;
			}
		}

		System.out.print(cnt);
	}
}