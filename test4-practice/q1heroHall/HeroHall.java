package q1heroHall;

public class HeroHall {
	/**
	 * Compute the maximum amount of treasure a hero can accumulate
	 * by running through a hall, with a choice of switching sides
	 * of the hall at each step. There is treasure on the left and
	 * right of each segment of the hall. Between each segment
	 * is a guardian who charges treasure for crossing the hall
	 * (there is no cost to go straight ahead, staying on the same
	 * side of the hall when entering a new segment).
	 * @param s Array storing the amounts of treasure at each segment
	 * on the left side of the hall
	 * @param t Array storing the amounts of treasure at each segment
	 * on the right side of the hall
	 * @param g Array storing the amount the guardians between segments
	 * charge. Note there is one less guardian than segments, and
	 * g[i] charges for crossing the hall between segments i and i+1.
	 * @return The maximum amount of treasure possible
	 */
	public static int bestTreasure(int[] s, int[] t, int[] g) {
		int[][] best = new int[s.length][2];

		if (s.length > 0) {
			best[0][0] = s[0];
			best[0][1] = t[0];

			for (int i=1;i<s.length;i++) {
				
				int l=best[i-1][0],r=best[i-1][1];
				int c=g[i-1];
				best[i][0] += (l > r-c) ? 
						l : r-c;
				best[i][0] += s[i];
				best[i][1] += (r > l-c) ?
						r : l-c;
				best[i][1] += t[i];
			}

			int n = s.length-1;
			return (best[n][0] > best[n][1]) ? best[n][0] : best[n][1];
		}
		return 0;
	}

}
