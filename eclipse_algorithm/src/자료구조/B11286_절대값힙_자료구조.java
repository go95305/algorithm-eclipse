package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class B11286_절대값힙_자료구조 {
	static class Point implements Comparable<Point> {
		int num;

		Point(int num) {
			this.num = num;
		}

		@Override
		public int compareTo(Point o) {
			if (Math.abs(this.num) == Math.abs(o.num))
				return this.num - o.num;
			else {
				return Math.abs(this.num) - Math.abs(o.num);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Point> pq = new PriorityQueue<Point>();

		int num = 0;
		for (int i = 0; i < N; i++) {
			num = Integer.parseInt(br.readLine());
			if (num == 0) {
				if (pq.size() > 0)
					System.out.println(pq.poll().num);
				else
					System.out.println(0);
			} else {
				pq.add(new Point(num));
			}
		}

	}
}
