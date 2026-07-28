import java.util.Objects;
// This class represents the universe in which the player will be moving around. It contains a grid of integers that represent the different locations in the universe
// At this time, I am not sure how the grid will contain objects or ships, etc, but I hope to work that out in the future.
public class Universe {
    // A grid whose coordinates can contain only one object.
    private static UniverseObject[][] grid = new UniverseObject[10][10];

    // Gets an object from the grid with individual arguments for x and y
    public static UniverseObject getObject(int[] c) {
        int x = c[0];
        int y = c[1];
        if ((x < 10) && (y < 10) && (x >= 0) && (y >= 0)) {
            if (grid[x][y] == null) {
                return null;
            } else {
                return grid[x][y];
            }
        }
        return null;
    }

    // Gets an object from the grid with individual arguments for x and y
    public static UniverseObject getObject(int x, int y) {
        if ((x < 10) && (y < 10) && (x >= 0) && (y >= 0)) {
            if (grid[x][y] == null) {
                return null;
            } else {
                return grid[x][y];
            }
        }
        return null;
    }

    // Sets an object on the grid using an array with index 0 = x and index 1 = y
    public static void setObject(int[] c, UniverseObject object) {
        int x = c[0];
        int y = c[1];
        if ((x < 10) && (y < 10) && (x >= 0) && (y >= 0)) {
            if (grid[x][y] != null) {
                grid[x][y] = object;
            }
        }
    }
// Sets an object on the grid using an array with individual arguments for x and y
    public static void setObject(int x, int y, UniverseObject object) {
        if ((x < 10) && (y < 10) && (x >= 0) && (y >= 0)) {
            if (grid[x][y] != null) {
                grid[x][y] = object;
            }
        }
    }

    // Removes an object on the grid
    public static void removeObject(UniverseObject object) {
        int[] c = findObject(object);
        grid[c[0]][c[1]] = null;
    }

    // Removes an object at a specific location on the grid using an array with index 0 = x and index 1 = y
    public static void removeObject(int[] c) {
        int x = c[0];
        int y = c[1];
        if ((x < 10) && (y < 10) && (x >= 0) && (y >= 0)) {
            if (grid[x][y] != null) {
                grid[x][y] = null;
            }
        }
    }

    // Removes an object at a specific location on the grid using an array with individual arguments for x and y
    public static void removeObject(int x, int y) {
        if ((x < 10) && (y < 10) && (x >= 0) && (y >= 0)) {
            if (grid[x][y] != null) {
                grid[x][y] = null;
            }
        }
    }

    /**
     * Gets the size of the grid on the x-axis.
     * @return the number of columns in the grid
     * @author Cooper Lauer
     * @date 4/25/2026
     */
    public static int getBoundsX() {
        return grid[0].length;
    }

    /**
     * Gets the size of the grid on the y-axis.
     * @return the number of rows in the grid
     * @author Cooper Lauer
     * @date 4/25/2026
     */
    public static int getBoundsY() {
        return grid.length;
    }

}
