package 구현_시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B14891_톱니바퀴 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int arr[][] = new int[4][8];

		/* dq에 입력 */
		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}

		/* 회전횟수 */
		int turn = Integer.parseInt(br.readLine());

		for (int i = 0; i < turn; i++) {
			boolean isSpin[] = new boolean[4];
			int way[] = new int[4];
			Arrays.fill(way, 1);
			Arrays.fill(isSpin, false);
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int wheel = Integer.parseInt(st.nextToken()) - 1;

			/*
			 * 1: 시계방향 2: 반시계방향
			 */
			int dir = Integer.parseInt(st.nextToken());

			if (wheel == 0) {
				isSpin[0] = true;
				way[0] = dir;
				// 2번 회전 시키고나서 3,4번을 본다.
				if (arr[0][2] != arr[1][6]) {
					isSpin[1] = true;
					way[1] = -way[0];
					if (arr[1][2] != arr[2][6]) {
						isSpin[2] = true;
						way[2] = -way[1];
						if (arr[2][2] != arr[3][6]) {
							isSpin[3] = true;
							way[3] = -way[2];
						}
					}
				} // 2번이 회전 안되면 3,4번은 그대로 이므로 else를 안쓴다.
				turn(isSpin, arr, way); // 회전해주는 메소드
			} else if (wheel == 1) {
				isSpin[1] = true;
				way[1] = dir;
				if (arr[1][6] != arr[0][2]) {
					isSpin[0] = true;
					way[0] = -way[1];
				}
				if (arr[1][2] != arr[2][6]) {
					isSpin[2] = true;
					way[2] = -way[1];
					if (arr[2][2] != arr[3][6]) {
						isSpin[3] = true;
						way[3] = -way[2];
					}
				}
				turn(isSpin, arr, way); // 회전해주는 메소드
			} else if (wheel == 2) {
				isSpin[2] = true;
				way[2] = dir;
				if (arr[2][6] != arr[1][2]) {
					isSpin[1] = true;
					way[1] = -way[2];
					if (arr[1][6] != arr[0][2]) {
						isSpin[0] = true;
						way[0] = -way[1];
					}
				}
				if (arr[2][2] != arr[3][6]) {
					isSpin[3] = true;
					way[3] = -way[2];
				}
				turn(isSpin, arr, way); // 회전해주는 메소드
			} else {
				isSpin[3] = true;
				way[3] = dir;
				if (arr[3][6] != arr[2][2]) {
					isSpin[2] = true;
					way[2] = -way[3];
					if (arr[2][6] != arr[1][2]) {
						isSpin[1] = true;
						way[1] = -way[2];
						if (arr[1][6] != arr[0][2]) {
							isSpin[0] = true;
							way[0] = -way[1];
						}
					}
				}
				turn(isSpin, arr, way); // 회전해주는 메소드
			}
		}

		int ans = getSum(arr);
		System.out.println(ans);

	}

	private static int getSum(int[][] arr) {
		int sum = 0;
		if (arr[0][0] == 1)
			sum += 1;
		if (arr[1][0] == 1)
			sum += 2;
		if (arr[2][0] == 1)
			sum += 4;
		if (arr[3][0] == 1)
			sum += 8;
		return sum;
	}

	private static void turn(boolean isSpin[], int[][] arr, int way[]) {
		for (int i = 0; i < 4; i++) {
			if (isSpin[i]) {
				spin(isSpin, i, arr, way);
			}
		}
	}

	private static void spin(boolean[] isSpin, int idx, int[][] arr, int way[]) {
		int first = 0;
		if (way[idx] == 1) {
			// 시계방향 회전(오른쪽)
			first = arr[idx][7];
			for (int i = arr[idx].length - 1; i > 0; i--) {
				int tmp = arr[idx][i];
				arr[idx][i] = arr[idx][i - 1];
				arr[idx][i - 1] = tmp;
			}
			arr[idx][0] = first;
		} else {
			// 반시계방향 회전
			first = arr[idx][0];
			for (int i = 1; i < arr[idx].length; i++) {
				int tmp = arr[idx][i];
				arr[idx][i] = arr[idx][i - 1];
				arr[idx][i - 1] = tmp;
			}
			arr[idx][7] = first;
		}

	}

}
