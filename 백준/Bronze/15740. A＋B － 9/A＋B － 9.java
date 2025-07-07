import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String val_a = st.nextToken();
        String val_b = st.nextToken();

        BigInteger a = new BigInteger(String.valueOf(val_a));
        BigInteger b = new BigInteger(String.valueOf(val_b));

        System.out.print(a.add(b));

    }
}
