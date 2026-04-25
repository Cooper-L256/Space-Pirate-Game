import java.util.Random;

/**
 * Represents a ship in the game with movement, combat, and upgrade capabilities.
 * Ships have health points, a damage range for attacks, and upgrade slots.
 * @author Cooper Lauer
 * @date 4/19/2026
 */
public class Ship extends UniverseObject {
 /** The maximum durability of the ship, set at creation and cannot be changed. */
 int durability;
 /** Current health points of the ship. Starts at durability and decreases when taking damage. */
 int hp;
 /** Array containing minimum and maximum damage values for attacks. Index 0 is min, index 1 is max. */
 int[] damageRange = new int[2];
 /** Array of up to 3 upgrades installed on the ship. Slots can be null if no upgrade is installed. */
 String[] upgrades = new String[3];
 /** Random number generator for calculating damage in attacks. */
 Random rand = new Random();

/**
 * Constructs a new Ship with default values.
 * Initializes health points to durability, sets damage range to 1-5.
 * @author Cooper Lauer
 * @date 4/25/2026
 */
public Ship () {
   super();
   durability = 10;
   hp = durability;
damageRange[0] = 1;
damageRange[1] = 5;
}

/**
 * Gets the current health points of the ship.
 * @return the current HP value
 * @author Cooper Lauer
 * @date 4/20/2026
 */
public int getHP () {
   return hp;
}

/**
 * Sets the current health points of the ship.
 * @param hp the new health point value
 * @author Cooper Lauer
 * @date 4/20/2026
 */
public void setHP (int hp) {
   this.hp = hp;
}

/**
 * Gets the damage range of this ship.
 * @return array containing minimum (index 0) and maximum (index 1) damage values
 * @author Cooper Lauer
 * @date 4/20/2026
 */
public int[] getDamageRange () {
   return damageRange;
}

/**
 * Sets the damage range of this ship.
 * @param min the minimum damage value
 * @param max the maximum damage value
 * @author Cooper Lauer
 * @date 4/20/2026
 */
public void setDamageRange (int min, int max) {
   damageRange[0] = min;
   damageRange[1] = max;
}

/**
 * Moves the ship forward (decreasing Y coordinate, wrapping around if needed).
 * @author Cooper Lauer
 * @date 4/25/2026
 */
public void moveForward () {
   int newY;
   if (super.y - 1 >= 0) {
newY = super.y - 1;
   } else {
      newY = Universe.getBoundsY() - 1;
   }

   Universe.setObject(super.x, newY, this);
}

/**
 * Moves the ship backward (increasing Y coordinate, wrapping around if needed).
 * @author Cooper Lauer
 * @date 4/25/2026
 */
public void moveBackward () {
   int newY;
   if (super.y + 1 < Universe.getBoundsY()) {
newY = super.y + 1;
   } else {
      newY = 0;
   }
   
   Universe.setObject(super.x, newY, this);
}

/**
 * Moves the ship left (decreasing X coordinate, wrapping around if needed).
 * @author Cooper Lauer
 * @date 4/25/2026
 */
public void moveLeft () {
   int newX;
   if (super.x - 1 >= 0) {
newX = super.x - 1;
   } else {
      newX = Universe.getBoundsX() - 1;
   }

   Universe.setObject(newX, super.y, this);
}

/**
 * Moves the ship right (increasing X coordinate, wrapping around if needed).
 * @author Cooper Lauer
 * @date 4/25/2026
 */
public void moveRight () {
   int newX;
   if (super.x + 1 < Universe.getBoundsX()) {
newX = super.x + 1;
   } else {
      newX = 0;
   }
   
   Universe.setObject(newX, super.y, this);;
}

/**
 * Reduces the ship's health points by the specified damage amount.
 * @param damage the amount of damage to take
 * @author Cooper Lauer
 * @date 4/20/2026
 */
public void takeDamage (int damage) {
   hp -= damage;
}

/**
 * Attacks another ship, dealing random damage within the ship's damage range.
 * The damage dealt is a random value between damageRange[0] and damageRange[1].
 * @param target the Ship to attack
 * @author Cooper Lauer
 * @date 4/20/2026
 */
public void attack (Ship target) {
   int damage = rand.nextInt(damageRange[1] - damageRange[0]) + damageRange[0];
   target.takeDamage(damage);
}  

/**
 * Installs an upgrade in a specific slot on this ship.
 * Each slot can hold one upgrade. Installing a new upgrade will overwrite any existing upgrade in that slot.
 * Valid slots are 0, 1, and 2.
 * @param upgrade the name of the upgrade to install
 * @param slot the slot index (0, 1, or 2) where the upgrade should be installed
 * @author Cooper Lauer
 * @date 4/19/2026
 */
public void installUpgrade (String upgrade, int slot) {
upgrades[slot] = upgrade;
 }

}     

/**
 * An assault variant of the Ship with increased damage but lower durability.
 * AssaultShips have 8 durability and deal 3-5 damage per attack.
 * @author Cooper Lauer
 * @date 4/25/2026
 */
class AssaultShip extends Ship {
  
/**
 * Constructs a new AssaultShip with combat-focused stats.
 * Initializes with 8 durability and damage range of 3-5.
 * @author Cooper Lauer
 * @date 4/25/2026
 */
public AssaultShip () {
   super();
   durability = 8;
   hp = durability;
damageRange[0] = 3;
damageRange[1] = 5;
}
}

/**
 * A heavy variant of the Ship with increased durability but lower damage.
 * HeavyShips have 15 durability and deal 1-3 damage per attack.
 * @author Cooper Lauer
 * @date 4/25/2026
 */
class HeavyShip extends Ship {

/**
 * Constructs a new HeavyShip with defensive-focused stats.
 * Initializes with 15 durability and damage range of 1-3.
 * @author Cooper Lauer
 * @date 4/25/2026
 */
public HeavyShip () {
   super();
   durability = 15;
   hp = durability;
damageRange[0] = 1;
damageRange[1] = 3;
}


}