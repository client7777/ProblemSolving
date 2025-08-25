import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int val = Integer.parseInt(br.readLine());

        if(val >= 380 && val < 425){
            System.out.print("Violet");
        }
        else if(val >= 425 && val < 450){
            System.out.print("Indigo");
        }
        else if(val >= 450 && val < 495){
            System.out.print("Blue");
        }
        else if(val >= 495 && val < 570){
            System.out.print("Green");
        }
        else if(val >= 570 && val < 590){
            System.out.print("Yellow");
        }
        else if(val >= 590 && val < 620){
            System.out.print("Orange");
        }
        else if(val >= 620 && val <= 780){
            System.out.print("Red");
        }
    }
}