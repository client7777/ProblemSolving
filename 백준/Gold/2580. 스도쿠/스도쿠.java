import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main
{
    static int[][] map;
    static ArrayList<int[]> zero_pos = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[9][9];

        StringTokenizer st;
        for(int i=0; i<9; i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0; j<9; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)
                {
                    zero_pos.add(new int[]{i,j});
                }
            }
        }

        sudoku(0);
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                sb.append(map[i][j] + " ");
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static boolean sudoku(int depth)
    {
        if(depth == zero_pos.size())
        {
            return true;
        }
        int x = zero_pos.get(depth)[0];
        int y = zero_pos.get(depth)[1];

        for(int i=1; i<=9; i++)
        {
            if(isValid(x,y,i))
            {
                map[x][y] = i;
                if(sudoku(depth+1))
                {
                    return true;
                }
                map[x][y] = 0;
            }
        }
        return false;
    }
    static boolean isValid(int x,int y,int num)
    {
        for(int i=0; i<9; i++)
        {
            if(map[x][i] == num || map[i][y] == num) return false;
        }
        int startRow = (x/3) * 3;
        int startCol = (y/3) * 3;

        for(int i=startRow; i<startRow+3; i++)
        {
            for(int j = startCol; j < startCol+3; j++)
            {
                if(map[i][j] == num) return false;
            }
        }
        return true;
    }
}
