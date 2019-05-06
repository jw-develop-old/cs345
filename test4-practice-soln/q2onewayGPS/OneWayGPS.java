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

        //begin solution, replace with: throw new UnsupportedOperationException
        int[][] travelTimes = new int[eastLength][northLength];
        for (int i = 1; i < eastLength; i++)
            travelTimes[i][0] = travelTimes[i-1][0] + eastBound[i-1][0];
        for (int j = 1; j < northLength; j++)
            travelTimes[0][j] = travelTimes[0][j-1] + northBound[0][j-1];
        for (int i = 1; i < eastLength; i++)
            for (int j = 1; j < northLength; j++) {
                int fromWest = travelTimes[i-1][j] + eastBound[i-1][j];
                int fromSouth = travelTimes[i][j-1] + northBound[i][j-1];
                travelTimes[i][j] = fromWest < fromSouth? fromWest : fromSouth;
            }

        return travelTimes[eastLength-1][northLength-1];
        // end solution
    }

}
