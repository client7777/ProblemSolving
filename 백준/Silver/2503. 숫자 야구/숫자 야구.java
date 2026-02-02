import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[1000];
		for(int i = 123; i <= 987; i++){
			String num = Integer.toString(i);

			if(num.charAt(0) == num.charAt(1) || num.charAt(0) == num.charAt(2) || num.charAt(1) == num.charAt(2) ||
				(num.charAt(0) == '0' || num.charAt(1) == '0' || num.charAt(2) == '0')){
				arr[i] = -1;
			}
		}

		StringTokenizer st;
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			String q = st.nextToken();
			int strike = Integer.parseInt(st.nextToken());
			int ball = Integer.parseInt(st.nextToken());

			for(int idx = 123; idx <= 987; idx++){

				if(arr[idx] == -1){
					continue;
				}

				int strikeCnt = 0;
				int ballCnt = 0;

				String queryNum = Integer.toString(idx);
				for(int j = 0; j < 3; j++){
					if(q.charAt(j) == queryNum.charAt(j)){
						strikeCnt++;
					}
				}

				if(strikeCnt != strike){
					continue;
				}

				for(int k = 0; k < 3; k++){
					for(int l = 0; l < 3; l++){
						if(q.charAt(k) == queryNum.charAt(l) && (k != l)){
							ballCnt++;
						}
					}
				}

				if(ball != ballCnt){
					arr[idx] = -1;
				}
				else{
					arr[idx]++;
				}
			}
		}

		int answer = 0;

		for(int i = 123; i <= 987; i++){
			if(arr[i] != -1 && arr[i] == n){
				answer++;
			}
		}

		System.out.print(answer);
	}
}