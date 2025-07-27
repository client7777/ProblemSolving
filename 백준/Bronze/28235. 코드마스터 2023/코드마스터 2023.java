import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        switch (str){
            case "SONGDO":
                System.out.print("HIGHSCHOOL");
                break;
            case "CODE":
                System.out.print("MASTER");
                break;
            case "2023":
                System.out.print("0611");
                break;
            case "ALGORITHM":
                System.out.print("CONTEST");
                break;

        }
    }
}
