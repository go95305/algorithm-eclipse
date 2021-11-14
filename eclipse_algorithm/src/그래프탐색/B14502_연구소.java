package 그래프탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14502_연구소 {
	static int N, M;
	static int map[][];
	static int zero;
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static List<Point> virus = new ArrayList<>();
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					zero++;
				if (map[i][j] == 2) {
					virus.add(new Point(i, j));
				}
			}
		}
		ans = 0;
		wall(0);
		System.out.println(ans);
	}

	private static void wall(int depth) {
		if (depth == 3) {
			virusSpread();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					wall(depth + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	private static void virusSpread() {
		// map을 copy한다.
		int[][] copy = new int[N][M];
		copyWall(copy);

		Queue<Point> que = new LinkedList<>();
		// 바이러스 위치 담기.
		for (int i = 0; i < virus.size(); i++) {
			que.add(virus.get(i));
		}

		int cnt = zero-3;
		while (!que.isEmpty()) {
			Point p = que.poll();

			for (int k = 0; k < 4; k++) {
				int nr = p.r + dr[k];
				int nc = p.c + dc[k];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && copy[nr][nc] == 0) {
					copy[nr][nc] = 1;
					que.add(new Point(nr, nc));
					cnt--;
				}
			}
		}

		ans = Math.max(ans, cnt);

	}

	private static void copyWall(int[][] copy) {
		for (int i = 0; i < N; i++) {
			copy[i] = map[i].clone();
		}
	}
}
