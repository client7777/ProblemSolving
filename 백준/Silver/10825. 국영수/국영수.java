import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		PriorityQueue<Student> pq = new PriorityQueue<>();

		StringTokenizer st;
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int korea = Integer.parseInt(st.nextToken());
			int math = Integer.parseInt(st.nextToken());
			int eng = Integer.parseInt(st.nextToken());

			pq.add(new Student(name, korea, math, eng));
		}

		StringBuilder sb = new StringBuilder();

		while(!pq.isEmpty()){
			sb.append(pq.poll().name).append("\n");
		}

		System.out.print(sb);
	}

	static class Student implements Comparable<Student>{
		String name;
		int korea;
		int eng;
		int math;

		public Student(String name, int korea, int eng, int math) {
			this.name = name;
			this.korea = korea;
			this.eng = eng;
			this.math = math;
		}

		@Override
		public int compareTo(Student o) {

			// 1. 국어 점수가 다르면: 국어 점수 내림차순
			if (this.korea != o.korea)
				return Integer.compare(o.korea, this.korea);

			// 2. 국어가 같으면: 영어 점수 오름차순
			if (this.eng != o.eng)
				return Integer.compare(this.eng, o.eng);

			// 3. 국어, 영어가 같으면: 수학 점수 내림차순
			if (this.math != o.math)
				return Integer.compare(o.math, this.math);

			// 4. 모든 점수가 같으면: 이름 사전순 오름차순
			return this.name.compareTo(o.name);
		}
	}
}