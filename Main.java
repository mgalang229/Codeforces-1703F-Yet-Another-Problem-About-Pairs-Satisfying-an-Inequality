import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*

a[i] < (i+1) < a[j] < (j+1)

i think sorting will be part of the solution
either sort by value or its index

1 2 3 4 5 6 7 8 (indices)
1 1 2 3 8 2 1 4 (values)

1 < 2 < 3 < 4
1 < 2 < 4 < 8
2 < 3 < 4 < 8

answer = 3

1 2 3 4 5 6 7 8
1 1 2 3 8 2 1 4

1 2 7 3 6 4 8 5
1 1 1 2 2 3 4 8

disregard the values which are less than their indices

2 7 3 6 4 8
1 1 2 2 3 4

-----------------------------------

1 2 3 4 5 6 7 8 9 10
0 2 1 6 3 4 1 2 8 3

after sorting:

1 3 7 8 5 10 6 9 
0 1 1 2 3  3 4 8 

0 < 1 < 2 < 8
0 < 1 < 3 < 5
0 < 1 < 3 < 10
0 < 1 < 4 < 6
0 < 1 < 8 < 9
1 < 3 < 4 < 6
1 < 3 < 8 < 9
1 < 7 < 8 < 9
3 < 5 < 8 < 9
4 < 6 < 8 < 9

 */

public class Main {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt();
			int[] a = fs.readArray(n);
			ArrayList<Pair> arr = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				if (a[i] < i + 1) {
					arr.add(new Pair(a[i], i + 1));
				}
			}
			Collections.sort(arr);
			long ans = 0;
			int arrLen = arr.size();
			for (int i = 0; i < arrLen - 1; i++) {
				int curIndex = arr.get(i).index;
				int low = i + 1;
				int high = arrLen - 1;
				while (low <= high) {
					int mid = low + (high - low) / 2;
					int valCompared = arr.get(mid).val;
					int prev = arr.get(mid-1).val;
					if (valCompared > curIndex) {
						if (prev <= curIndex) {
							ans += arrLen - mid;
							break;
						}
						high = mid - 1;
					} else {
						low = mid + 1;
					}
				}
			}
			out.println(ans);
		}
		out.close();
	}
	
	static class Pair implements Comparable<Pair> {
		int val;
		int index;
		
		public Pair(int val, int index) {
			this.val = val;
			this.index = index;
		}
		
		@Override
		public int compareTo(Pair o) {
			if (this.val < o.val) {
				return -1;
			}
			if (this.val == o.val) {
				if (this.index < o.index) {
					return -1;
				}
				return 1;
			}
			return 1;
		}
	}
	
	static void sort(int[] a) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int x : a) {
			arr.add(x);
		}
		Collections.sort(arr);
		for (int i = 0; i < a.length; i++) {
			a[i] = arr.get(i);
		}
	}
	
	static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
