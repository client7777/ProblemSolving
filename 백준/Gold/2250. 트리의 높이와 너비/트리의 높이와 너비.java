import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] rank;
	static Node[] tree;
	static int[] max;
	static int[] min;
	static int index = 1;
	static int maxDepth = -1;
	static int answerLevel = -1;
	static int answerWidth = -1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		rank = new int[n+1];
		tree = new Node[n+1];
		max = new int[n+1];
		min = new int[n+1];

		for(int i = 1; i <= n; i++) {
			tree[i] = new Node(-1, -1);
			max[i] = Integer.MIN_VALUE;
			min[i] = Integer.MAX_VALUE;
		}

		StringTokenizer st;
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int mid = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			tree[mid] = new Node(left, right);

			if(left != -1){
				rank[left]++;
			}

			if(right != -1){
				rank[right]++;
			}
		}

		int root = -1;
		for(int i = 1; i <= n; i++) {
			if(rank[i] == 0){
				root = i;
				break;
			}
		}

		dfs(root, 1);
		for(int i = 1; i <= maxDepth; i++) {

			int width = max[i] - min[i] + 1;

			if(width > answerWidth){
				answerWidth = width;
				answerLevel = i;
			}
		}

		System.out.print(answerLevel + " " + answerWidth);
	}

	static void dfs(int node, int level){
		Node curNode = tree[node];
		maxDepth = Math.max(maxDepth, level);

		//중위 순회
		//left -> root -> right

		//left
		if(curNode.left != -1){
			dfs(curNode.left, level+1);
		}

		//root
		min[level] = Math.min(min[level], index);
		max[level] = Math.max(max[level], index++);

		//right
		if(curNode.right != -1){
			dfs(curNode.right, level+1);
		}
	}

	static class Node{
		int left;
		int right;

		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}

}