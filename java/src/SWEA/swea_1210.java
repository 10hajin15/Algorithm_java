package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_1210 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        for(int t=0; t<10; t++) {

            int tc = Integer.parseInt(br.readLine());
            int[][] arr = new int[100][102];

            int ci = 99;
            int cj = 0;

            for(int i = 0; i < 100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for(int j = 1; j <= 100; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    arr[i][j] = num;
                    if(num == 2) cj = j;
                }
            }

            while (ci > 0) {
                arr[ci][cj] = 0;
                if(arr[ci][cj-1] == 1) {
                    cj -= 1;
                } else if(arr[ci][cj+1] == 1) {
                    cj += 1;
                } else {
                    ci -= 1;
                }
            }

            System.out.println(String.format("#%d %d", tc, cj-1));
        }
    }
}
