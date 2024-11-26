package Backtracking;
// 최악의 경우 각 칸마다 숫자 1부터 9까지 탐색을 시도할 수 있음. 최악의 경우 모든 숫자를 시도해야 하는 상황 발생
// 최악의 경우 시간복잡도 ->9^81
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_2580
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
                    zero_pos.add(new int[]{i,j}); // 빈칸의 좌표를 저장
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
            return true; // 모든 빈칸의 숫자를 정했으면 탐색 종료
        }
        int x = zero_pos.get(depth)[0];
        int y = zero_pos.get(depth)[1];

        for(int i=1; i<=9; i++)
        {
            if(isValid(x,y,i))
            {
                map[x][y] = i; // 숫자 i를 현재 위치에 채움
                if(sudoku(depth+1))
                {
                    return true; // 다음 단계에서 성공했다면 true 반환
                }
                map[x][y] = 0; // 실패한 경우 현재 위치 초기화
            }
        }
        return false;
    }
    static boolean isValid(int x,int y,int num)
    {
        //행과 열에 num과 같은 숫자가 있는지 검사
        for(int i=0; i<9; i++)
        {
            if(map[x][i] == num || map[i][y] == num) return false;
        }
        int startRow = (x/3) * 3;
        int startCol = (y/3) * 3;

        //3*3 격자 확인
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
