import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		String[][] board = {{"11", "A B C D E F G H J L M"},
			{"9", "A C E F G H I L M"},
			{"9", "A C E F G H I L M"},
			{"9", "A B C E F G H L M"},
			{"8", "A C E F G H L M"},
			{"8", "A C E F G H L M"},
			{"8", "A C E F G H L M"},
			{"8", "A C E F G H L M"},
			{"8", "A C E F G H L M"},
			{"8", "A B C F G H L M"}};

		System.out.println(board[n-1][0]);
		System.out.print(board[n-1][1]);
	}
}