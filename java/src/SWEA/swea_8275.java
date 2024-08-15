package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_8275 {
    static int N, X, M;
    static int[][] memo;
    static int sum;
    static int[] ans;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static boolean isRightMemo(int [] lst) {
        for(int m=0; m<M; m++) {
            int tSum = 0;
            int l = memo[m][0]-1;
            int r = memo[m][1]-1;
            int s = memo[m][2];

            for(int i =l; i<=r; i++) {
                tSum += lst[i];
            }

            if(tSum != s) return false;
        }
        return true;
    }

    static void dfs(int n, int[] lst, int lsum) {
        if (n == N) {
            // memo 조건 맞는지
            if(!isRightMemo(lst)) return;

            // 햄스터 수가 더 많은지 & 사전 순 check
            if(sum < lsum) {
                sum = lsum;
                ans = lst.clone();
            }
            return;
        }

        for(int i=0; i<=X; i++) {
            lst[n] = i;
            dfs(n+1, lst, lsum+i);
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            ans = new int[N];
            sum = Integer.MIN_VALUE;

            memo = new int[M][3];
            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j =0; j<3; j++) {
                    memo[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[] lst = new int[N];
            dfs(0, lst, 0);

            System.out.print("#"+tc+" ");
            if(sum == Integer.MIN_VALUE) {
                System.out.println(-1);
            } else {
                for(int i=0; i<N; i++) {
                    System.out.print(ans[i]+" ");
                }
                System.out.println();
            }
        }
    }
}
