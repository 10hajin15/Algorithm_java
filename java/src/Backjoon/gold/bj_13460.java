package Backjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_13460 {
    static int N, M;
    static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    static int ans = Integer.MAX_VALUE;

    static void dfs(int n, String[] arr,  int[] rPos, int[] bPos) {
        if(n >= 10) return;
        int ri, rj, bi, bj;

        for(int i = 0; i<4; i++) {
            boolean redHole = false, blueHole = false;
            ri = rPos[0]; rj = rPos[1];
            bi = bPos[0]; bj = bPos[1];

            // 배열 복사
            String[] newArr = arr.clone();
            for(int k=0; k<N;k++) {
                newArr[k] = new String(arr[k]);
            }

            // 빨간 구슬 이동
            while(true) {
                int ni = ri + dirs[i][0];
                int nj = rj + dirs[i][1];

                char ch = newArr[ni].charAt(nj);

                if(ch == '#') {
                    break; // 벽을 만나면 이동 중지
                } else if(ch == 'O') {
                    redHole = true; // 빨간 구슬이 구멍에 들어감
                    break;
                } else {
                    ri = ni;
                    rj = nj;
                }
            }

            // 파란 구슬 이동
            while(true) {
                int ni = bi + dirs[i][0];
                int nj = bj + dirs[i][1];

                char ch = newArr[ni].charAt(nj);

                if(ch == '#') {
                    break; // 벽을 만나면 이동 중지
                } else if(ch == 'O') {
                    blueHole = true; // 파란 구슬이 구멍에 들어감
                    break;
                } else {
                    bi = ni;
                    bj = nj;
                }
            }

            // 빨간 구슬과 파란 구슬이 같은 위치에 있는 경우 처리
            if(ri == bi && rj == bj) {
                // 더 많이 이동한 구슬을 한 칸 뒤로 이동
                if (Math.abs(ri - rPos[0]) + Math.abs(rj - rPos[1]) > Math.abs(bi - bPos[0]) + Math.abs(bj - bPos[1])) {
                    ri -= dirs[i][0];
                    rj -= dirs[i][1];
                } else {
                    bi -= dirs[i][0];
                    bj -= dirs[i][1];
                }
            }

            char[] tmp = newArr[ri].toCharArray();
            tmp[rj] = 'R';
            newArr[ri] = String.valueOf(tmp);

            char[] tmp2 = newArr[bi].toCharArray();
            tmp2[bj] = 'B';
            newArr[bi] = String.valueOf(tmp2);

            if(blueHole) continue; // 파란 구슬이 구멍에 빠지면 실패

            if(redHole) { // 빨간 구슬이 구멍에 빠졌다면 성공
                ans = Math.min(ans, n + 1);
                return;
            }

            // 구슬들이 움직였을 경우에만 다음 재귀 호출
            if(ri != rPos[0] || rj != rPos[1] || bi != bPos[0] || bj != bPos[1]) {
                dfs(n+1, newArr, new int[] {ri, rj}, new int[] {bi, bj});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] rPos = new int[2];
        int[] bPos = new int[2];

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        String[] arr = new String[N];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            arr[i] = str;
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j<M; j++) {
                char ch = arr[i].charAt(j);
                if(ch == 'R') {
                    rPos[0] = i; rPos[1] = j;
                } else if(ch == 'B') {
                    bPos[0] = i; bPos[1] = j;
                }
            }
        }

        boolean[][] visited = new boolean[N][M];
        dfs(0, arr, rPos, bPos);

        if(ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

}