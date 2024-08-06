// 지뢰찾기
// https://www.acmicpc.net/problem/4108
package Backjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_4108 {
    static int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            if(row == 0 || col == 0) {
                break;
            }

            String [] sLst = new String[row];
            for (int i = 0; i<row; i++) {
                sLst[i] = br.readLine();
            }

            int[][] arr = new int[row][col];
            for(int i=0; i<row; i++) {
                for(int j=0; j<col; j++) {
                    if(sLst[i].charAt(j) == '*') {
                        arr[i][j] = -1;
                        for(int k=0; k<8; k++) {
                            int ni = i + dirs[k][0];
                            int nj = j + dirs[k][1];
                            if (0<=ni && ni<row && 0<=nj && nj<col && arr[ni][nj] != -1) {
                                arr[ni][nj] += 1;
                            }
                        }
                    }
                }
            }

            for(int i = 0; i<row; i++) {
                for(int j =0; j<col; j++) {
                    if (arr[i][j] == -1) {
                        System.out.print('*');
                    } else {
                        System.out.print(arr[i][j]);
                    }
                }
                System.out.println();
            }
        }

    }
}