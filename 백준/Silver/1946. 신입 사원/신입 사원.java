import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (testCase-- > 0) {
			int n = Integer.parseInt(br.readLine());

			Rank[] ranks = new Rank[n];

			StringTokenizer st;
			for(int i = 0; i < n; i++){
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int interview = Integer.parseInt(st.nextToken());

				ranks[i] = new Rank(score, interview);
			}

			Arrays.sort(ranks, Comparator.comparingInt(o -> o.score)); // 지원자들의 등수를 서류 점수로 오름차순 정렬

			int answer = 1;

			int min = ranks[0].interview; // 서류 점수가 1등인 지원자의 면접점수

			for(int i = 1; i < ranks.length; i++){
				if(ranks[i].interview < min){
					answer++;
					min = ranks[i].interview;
				}
			}
			sb.append(answer).append("\n");
		}

		System.out.println(sb);
	}

	static class Rank{
		int score;
		int interview;

		public Rank(int score, int interview) {
			this.score = score;
			this.interview = interview;
		}
	}
}