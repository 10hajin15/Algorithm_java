// 아기 상어
// https://www.acmicpc.net/problem/16236
package Backjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_16236 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    static int[][] arr;
    static int shark = 2;      // 상어 크기
    static int cnt = 0;        // 물고기 먹은 개수
    static int ans = 0;        // 정답~!

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        int ci = 0;
        int cj = 0;

        arr = new int[N][N];
        for(int i = 0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j =0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 9) {
                    ci = i;
                    cj = j;
                    arr[i][j] = 0;
                }
            }
        }

        while(true) {
            Shark sk = bfs(ci, cj);

            if(sk.tlst.isEmpty()) break;

            int[] cur = sk.tlst.poll();
            ci = cur[0];
            cj = cur[1];
            arr[ci][cj] = 0;
            cnt += 1;
            ans += sk.dist;

            if (shark == cnt) {
                cnt = 0;
                shark += 1;
            }

        }
        System.out.println(ans);
    }

    static class Shark {
        int dist;
        Queue<int[]> tlst = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return Integer.compare(o1[0], o2[0]);
                } else {
                    return Integer.compare(o1[1], o2[1]);
                }
            }
        });

    }

    static Shark bfs(int si, int sj) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] visited = new int[N][N];

        queue.offer(new int[] {si, sj});
        visited[si][sj] = 1;

        Shark shk = new Shark();

        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            int ci = node[0];
            int cj = node[1];
            if(shk.dist == visited[node[0]][node[1]]) {
                shk.dist -= 1;
                return shk;
            }

            for(int i = 0; i < 4; i++) {
                int ni = ci+dirs[i][0];
                int nj = cj+dirs[i][1];

                if (0<=ni && ni<N && 0<=nj && nj<N) {
                    if((arr[ni][nj] <= shark) && (visited[ni][nj] == 0)) {
                        queue.offer(new int[] {ni, nj});
                        visited[ni][nj] = visited[ci][cj] + 1;
                        if (0 < arr[ni][nj] && arr[ni][nj] < shark) {
                            shk.tlst.add(new int[] {ni, nj});
                            shk.dist = visited[ni][nj];
                        }

                    }
                }
            }
        }
        shk.dist -= 1;
        return shk;
    }
}
