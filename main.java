


// This is a begaining page when a user launches the program they will land on this page 1st like welcoming message (Theo)
public class Main {
   public static void main(String[] args) {
      System.out.println("Hello Ship");
      // Create a ship
      Ship ship = new Ship(10);
   System.out.println("Ship is currently at coordinates ("+ship.x+", "+ship.y+")");
   }
}
