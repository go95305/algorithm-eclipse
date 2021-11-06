package ����Ž��;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class �����Ǻ� {
	static int N, H, D;
	static char map[][];
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static int sr, sc;
	static boolean v[][][];
	static int ans;

	static class Point {
		int r, c, hp, cnt, isUm, um;

		Point(int r, int c, int hp, int cnt, int isUm, int um) {
			this.r = r;
			this.c = c;
			this.hp = hp;
			this.cnt = cnt;
			this.isUm = isUm;
			this.um = um;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'S') {
					sr = i;
					sc = j;
				}
			}
		}

		ans = Integer.MAX_VALUE;
		v = new boolean[2][N][N];// ����� ���� ���� ���� �Ⱦ��� ������� �����Ѵ�.
		bfs();

		if (ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans);
	}

	private static void bfs() {
		Queue<Point> que = new LinkedList();
		que.add(new Point(sr, sc, H, 0, 0, 0));
		v[0][sr][sc] = true;
		while (!que.isEmpty()) {
			Point p = que.poll();
			if (map[p.r][p.c] == 'E') {
				ans = p.cnt;
				return;
			}
			for (int k = 0; k < 4; k++) {
				int nr = p.r + dr[k];
				int nc = p.c + dc[k];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[p.isUm][nr][nc] && map[nr][nc] != 'S') {
					if (map[nr][nc] == '.') {
						if (p.isUm == 1) {// ����� ���� �ִٸ�
							if (p.um - 1 > 0) { // ��� hp�� ���� ������
								v[p.isUm][nr][nc] = true;
								que.add(new Point(nr, nc, p.hp, p.cnt + 1, p.isUm, p.um - 1));
							} else {
								v[0][nr][nc] = true;
								if (p.hp - 1 > 0)
									que.add(new Point(nr, nc, p.hp - 1, p.cnt + 1, 0, 0));
							}
						} else {// ����� ���� ����������
							v[0][nr][nc] = true;
							if (p.hp - 1 > 0)
								que.add(new Point(nr, nc, p.hp - 1, p.cnt + 1, 0, 0));
						}
					} else if (map[nr][nc] == 'U') {
						// ����� ��ä�ε� �Ⱦ�ä�ε� �����ġ�� �����ϸ� ���ο� �������� ������� ����.
						v[1][nr][nc] = true;
						que.add(new Point(nr, nc, p.hp, p.cnt + 1, 1, D));
					} else if (map[nr][nc] == 'E') {
						v[p.isUm][nr][nc] = true;
						que.add(new Point(nr, nc, p.hp, p.cnt + 1, 1, D));
					}
				}

			}
		}

	}
}
