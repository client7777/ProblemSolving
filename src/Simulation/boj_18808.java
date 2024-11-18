package Simulation;
// 스티커를 특정 영역에 붙일 수 있는지 확인하고 붙이기
// 스티커 회전하기
import java.io.*;
import java.util.*;

public class boj_18808
{
    static int n,m,k;
    static int[][] note = new int[40][40];
    static int[][] paper = new int[10][10];
    static int r,c;

    public static void main(String[] args) throws IOException
    {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            while(k > 0)
            {
                st = new StringTokenizer(br.readLine());
                r = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                // 스티커 배열에 현재 스티커의 정보를 저장
                for(int i=0; i<r; i++)
                {
                    st = new StringTokenizer(br.readLine());
                    for(int j=0; j<c; j++)
                    {
                        paper[i][j] = Integer.parseInt(st.nextToken());
                    }
                }
                boolean is_paste = false; // 스티커를 붙일 수 있는지 여부를 저장
                // 4방향 회전 시도
                for(int rot = 0; rot<4; rot++)
                {
                    // 노트의 모든 위치에 대해 스티커를 붙일 수 있는지 확인
                    for(int x=0; x<=n-r; x++)
                    {
                        if(is_paste) break; // 이미 붙인 경우 더 이상 확인할 필요 없음
                        for(int y=0; y<=m-c; y++)
                        {
                            // 스티커를 붙일 수 있는지 확인
                            if(postable(x,y))
                            {
                                paste(x,y);
                                is_paste = true;
                                break;
                            }
                        }
                    }
                    if(is_paste) break; // 스티커를 붙인 경우 루프 종료
                    rotate(); // 스티커를 붙이지 못했으므로 다른 각도로 붙이기 시도
                }
                k--; // 스티커 하나를 소진
            }
            int ans = 0;
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<m; j++)
                {
                    if(note[i][j] == 1) ans++;
                }
            }
            System.out.print(ans);
    }
    //배열을 회전시키는 함수
    static void rotate()
    {
        int[][] tmp = new int[10][10];
        for(int i=0; i<r; i++)
        {
            for(int j=0; j<c; j++)
            {
                tmp[i][j] = paper[i][j];
            }
        }
        // 임시 배열을 90도 회전하여 스티커 배열에 저장
        for(int i=0; i<c; i++)
        {
            for(int j=0; j<r; j++)
            {
                paper[i][j] = tmp[r-1-j][i];
            }
        }
        // 회전 후 행과 열 크기를 교환
        int swap = r;
        r = c;
        c = swap;
    }
    // 노트에 스티커를 붙일 수 있는지 확인하는 함수
    static boolean postable(int x,int y)
    {   // x: 스티커의 상단 왼쪽이 위치할 노트의 행 인덱스
        // y: 스티커의 상단 왼쪽이 위치할 노트의 열 인덱스
        for(int i=0; i<r; i++)
        {
            for(int j=0; j<c; j++)
            {
                if(note[x+i][y+j] == 1 && paper[i][j] == 1) return false;
                //스티커의 셀과 노트의 셀이 모두 1이면 스티커의 셀과 노트의 셀이 겹쳐서 이미 스티커가 붙어있는 상태임
                //스티커를 그 위치에 붙일 수 없음
            }
        }
        // 스티커의 모든 셀을 검사했음에도 겹침이 없으면 스티커를 붙일 수 있는 위치라는 의미이므로 true 반환
        return true;
    }
    // 노트에 스티커를 붙이는 함수
    static void paste(int x,int y)
    {
        for(int i=0; i<r; i++)
        {
            for(int j=0; j<c; j++)
            {
                // 스티커의 해당 위치가 1이면 노트에 1을 붙임
                if(paper[i][j] == 1)
                {
                    note[x+i][y+j] = 1;
                }
            }
        }
    }
}