// 지뢰찾기
// https://www.acmicpc.net/problem/1996
package Backjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1996 {
    static int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] input = new String[N];

        for(int i = 0; i < N; i++) {
            input[i] = br.readLine();
        }

        int[][] arr = new int[N][N];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                char c = input[i].charAt(j);
                if (c != '.') {
                    arr[i][j] = -1;
                    int bCnt = c - '0';
                    for(int k = 0; k < 8; k++){
                        int ni = i + dirs[k][0];
                        int nj = j + dirs[k][1];
                        if (0 <= ni && ni < N && 0 <= nj && nj < N && arr[ni][nj] != -1) {
                            arr[ni][nj] += bCnt;
                        }
                    }
                }
            }
        }

        for(int x = 0; x < N; x++) {
            for(int y = 0; y < N; y++) {
                if (arr[x][y] == -1) {
                    System.out.print('*');
                } else if(arr[x][y] >= 10) {
                    System.out.print('M');
                } else {
                    System.out.print(arr[x][y]);
                }
            }
            System.out.println();
        }
    }
}