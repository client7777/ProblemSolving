import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		BigInteger n = new BigInteger(br.readLine());
		BigInteger start = BigInteger.ZERO;
		BigInteger end = n;

		while (true){
			BigInteger mid = start.add(end).divide(BigInteger.TWO);
			int result = mid.multiply(mid).compareTo(n);

			if(result == 0){
				System.out.print(mid);
				break;
			}
			else if(result > 0){
				end = mid.subtract(BigInteger.ONE);
			}
			else{
				start = mid.add(BigInteger.ONE);
			}
		}
	}
}