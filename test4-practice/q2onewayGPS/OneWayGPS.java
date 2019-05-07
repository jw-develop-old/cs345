package q2onewayGPS;

public class OneWayGPS {

    /**
     * Compute the shortest travel time from the southwest
     * corner to the northwest corner, given the east-bound and north-bound
     * travel times of of segments in the grid.
     * @param eastLength The number of intersections from west to east
     * @param northLength The number of intersections from north to south
     * @param eastBound The travel times of road segments heading east
     * @param northBound The travel times of road segments heading north
     * @return The shortest travel time of any route from the southwest
     * to the northeast. 
     */
    public static int getBestTravelTime(int eastLength, int northLength, 
            int[][] eastBound, int[][] northBound) {
    	
    	int[][] times = new int[eastLength][northLength];
    	
    	for (int i=0;i<eastLength;i++)
			times[i][0] = eastBound[i][0];
    	
    	for (int i=0;i<northLength;i++)
			times[0][i] = northBound[0][i];
    	
    	for (int i=1;i<eastLength;i++) {
        	for (int j=1;j<northLength;j++) {
        		int w = times[i-1][j] + eastBound[i-1][j];
        		int s = times[i][j-1] + northBound[i][j-1];
        		
        		times[i][j] = (w > s) ? s : w;
        	}
    	}
    	
    	return times[eastLength-1][northLength-1];
    }

}
