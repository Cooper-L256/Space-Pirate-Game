// This class represents the universe in which the player will be moving around. It contains a grid of integers that represent the different locations in the universe, as well as the player's current position (x and y coordinates)
// At this time, I am not sure how the grid will contain objects or ships, etc, but I hope to work that out in the future.
public class Universe {
    int[][] grid = new int[10][10]; // A 10x10 grid representing the universe
    int x;
    int y;
    public Universe() {
        // Set the coordinates to (0,0)
        x = 0;
        y = 0;
    }

}