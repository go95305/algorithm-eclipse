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

public class B16234_인구이동 {
	static int N, L, R;
	static int map[][];
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static int uni[][];
	static boolean flag;

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int step = 0;
		while (true) {
			uni = new int[N][N];
			int door = 0;
			int cnt = 0;
			int union = 1;
			flag = false;
			boolean v[][] = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!v[i][j]) {
						bfs(i, j, v, union);
						union++;
					}
				}
			}

			if (!flag) {
				break;
			}

			setNewVal();
			step++;
		}
		System.out.println(step);

	}

	private static void print() {
		for(int i=0;i<N;i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
		
	}

	private static void setNewVal() {
		boolean v[][] = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!v[i][j]) {
					connect(i, j, v);
				}
			}
		}

	}

	private static void connect(int r, int c, boolean[][] v) {
		int val = uni[r][c];
		v[r][c] = true;
		Queue<Point> que = new LinkedList<>();
		List<Point> list = new ArrayList<>();
		list.add(new Point(r, c));
		que.add(new Point(r, c));
		int sum = map[r][c];
		int cnt = 1;
		while (!que.isEmpty()) {
			Point p = que.poll();
			for (int k = 0; k < 4; k++) {
				int nr = p.r + dr[k];
				int nc = p.c + dc[k];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc] && uni[nr][nc] == val) {
					sum += map[nr][nc];
					v[nr][nc] = true;
					cnt++;
					que.add(new Point(nr, nc));
					list.add(new Point(nr, nc));
				}
			}
		}

		int set = sum / cnt;
		// 새로운 값으로 설정
		for (int i = 0; i < list.size(); i++) {
			Point p = list.get(i);
			map[p.r][p.c] = set;
		}
		

	}

	private static void bfs(int r, int c, boolean[][] v, int union) {

		Queue<Point> que = new LinkedList<>();
		que.add(new Point(r, c));
		v[r][c] = true;
		uni[r][c] = union;
		while (!que.isEmpty()) {
			Point p = que.poll();
			for (int k = 0; k < 4; k++) {
				int nr = p.r + dr[k];
				int nc = p.c + dc[k];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc] && Math.abs(map[p.r][p.c] - map[nr][nc]) >= L && Math.abs(map[p.r][p.c] - map[nr][nc]) <= R) {
					v[nr][nc] = true;
					flag = true;
					uni[nr][nc] = union;
					que.add(new Point(nr, nc));
				}
			}
		}

	}

}
