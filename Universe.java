// This class represents the universe in which the player will be moving around. It contains a grid of integers that represent the different locations in the universe
// At this time, I am not sure how the grid will contain objects or ships, etc, but I hope to work that out in the future.
public class Universe {
    // A grid whose coordinates can contain only one object.
    private static UniverseObject[][] grid = new UniverseObject[10][10];

    // Gets an object from the grid
    public static UniverseObject getObject(int x, int y) {
        if ((x < 10) && (y < 10) && (x >=0) && (y >= 0)) {
            if (grid[x][y] == null) {
                return null;
        } else {
                return grid[x][y];
        }
        }
        return null;
    }
    // Sets an object on the grid-- contains incomplete error handling, needs work
    public static void setObject(int x, int y, UniverseObject object) {
        if ((x < 10) && (y < 10) && (x >=0) && (y >= 0)) {
            grid[x][y] = object;
        }
    }

    /**
     * Gets the size of the grid on the x-axis.
     * @return the number of columns in the grid
     * @author Cooper Lauer
     * @date 4/25/2026
     */
    public static int getBoundsX () {
return grid[0].length;
}

    /**
     * Gets the size of the grid on the y-axis.
     * @return the number of rows in the grid
     * @author Cooper Lauer
     * @date 4/25/2026
     */
    public static int getBoundsY () {
return grid.length;
}

}