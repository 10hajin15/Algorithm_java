// 달팽이
// https://www.acmicpc.net/problem/1913
package Backjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1913 {
    static int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    static int N, num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        num = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];


        int i = 0;
        int j = 0;

        int cnt = N*N;
        arr[i][j] = cnt;
        cnt--;
        int dr = 0;

        while (cnt > 0) {
            int ni = i + dirs[dr][0];
            int nj = j + dirs[dr][1];

            if (0<=ni && ni<N && 0<=nj && nj<N && arr[ni][nj] ==0) {
                arr[ni][nj] = cnt;
                i = ni;
                j = nj;

                cnt--;
            } else {
                dr = (dr+1) % 4;
            }
        }

        int ai = 0, aj = 0;
        for(int x=0; x<N; x++) {
            for(int y=0; y<N; y++) {
                if(arr[x][y] == num) {
                    ai = x+1;
                    aj = y+1;
                }
                sb.append(arr[x][y] + " ");
            }
            sb.append("\n");
        }
        sb.append(ai + " "+ aj);

        System.out.println(sb);
    }
}