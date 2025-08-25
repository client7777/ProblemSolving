import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		StringTokenizer st = new StringTokenizer(br.readLine());
        
		int[] coord_1 = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		st = new StringTokenizer(br.readLine());
		int[] coord_2 = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		st = new StringTokenizer(br.readLine());
		int[] coord_3 = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
 
		
		
		int x;
		int y;
 
		if (coord_1[0] == coord_2[0]) {
			x = coord_3[0];
		}
		else if (coord_1[0] == coord_3[0]) {
			x = coord_2[0];
		}
		else {
			x = coord_1[0];
		}
		if (coord_1[1] == coord_2[1]) {
			y = coord_3[1];
		}
		else if (coord_1[1] == coord_3[1]) {
			y = coord_2[1];
		}
		else {
			y = coord_1[1];
		}
		
		System.out.println(x + " " + y);
	}
}