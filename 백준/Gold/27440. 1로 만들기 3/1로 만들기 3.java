import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static long n;
	static HashSet<Long> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Long.parseLong(br.readLine());

		System.out.print(bfs());
	}

	static int bfs(){

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(n, 0));

		set.add(n);

		while(!q.isEmpty()){
			Node cur = q.poll();
			long curNode = cur.n;
			int curCnt = cur.cnt;

			if(curNode == 1){
				return curCnt;
			}

			if(curNode % 3 == 0){
				long nNode = curNode / 3;

				if(!set.contains(nNode)){
					set.add(nNode);
					q.add(new Node(nNode, curCnt + 1));
				}
			}

			if(curNode % 2 == 0){
				long nNode = curNode / 2;
				if(!set.contains(nNode)){
					set.add(nNode);
					q.add(new Node(nNode, curCnt + 1));
				}
			}

			if(!set.contains(curNode-1)){

				q.add(new Node(curNode-1,  curCnt + 1));
				set.add(curNode-1);
			}
		}

		return -1;
	}

	static class Node{
		long n;
		int cnt;

		public Node(long n, int cnt) {
			this.n = n;
			this.cnt = cnt;
		}
	}
}