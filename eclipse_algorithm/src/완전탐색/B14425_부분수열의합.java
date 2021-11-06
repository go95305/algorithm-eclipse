package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class B14425_부분수열의합 {
	static int arr[];
	static boolean v[];
	static boolean num[];
	static int sel[];
	static int N;
	static HashSet set;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		v = new boolean[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		set = new HashSet();
		powerSet(0, 0);
		int idx = 1;
		int ans = 0;
		while (true) {
			if (!set.contains(idx)) {
				ans = idx;
				break;
			}
			idx++;
		}
		System.out.println(ans);
	}

	private static void powerSet(int idx, int k) {

		if (idx == arr.length) {
			int sum = 0;
			for (int i = 0; i < v.length; i++) {
				if (v[i]) {
					sum += arr[i];
				}
			}
			set.add(sum);
			return;
		}

		v[idx] = true;
		powerSet(idx + 1, k + 1);
		v[idx] = false;
		powerSet(idx + 1, k);
	}
}
