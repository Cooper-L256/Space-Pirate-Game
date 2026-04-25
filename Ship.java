import java.util.Random;

/**
 * Represents a ship in the game.
 * Ships have durability, health points, a damage range for attacks, and upgrades.
 * @author Cooper Lauer
 * @date 4/19/26
 */
public class Ship extends UniverseObject {
   /**
    * The maximum durability of the ship, set at creation and cannot be changed.
    */
   int durability;
   int baseDurability; // The durability that exists regardless of upgrades

   /**
    * Array containing minimum and maximum damage values for attacks. Index 0 is
    * min, index 1 is max.
    */
   int[] damageRange = new int[2];
   int[] baseDamageRange = new int[2];

   /**
    * Current health points of the ship. Starts at durability and decreases when
    * taking damage.
    */
   int hp;

   /**
    * Array of up to 3 upgrades installed on the ship. Slots can be null if no
    * upgrade is installed.
    */
   String[] upgrades = new String[3];

   /** Random number generator for calculating damage in attacks. */
   Random rand = new Random();

   /**
    * Constructs a Ship with a given durability and damage range.
    * Health points are initialized to the durability value.
    * 
    * @param durability  the maximum and initial health of the ship
    * @param damageRange array with minimum and maximum damage values
    * @author Cooper Lauer
    * @date 4/19/26
    */
   public Ship(String name, int[] coords, int durability, int[] damageRange) {
      super(name, coords);
      this.durability = durability;
      this.baseDurability = durability;
      hp = durability;
      this.damageRange = damageRange;
      this.baseDamageRange = damageRange;
   }

   /**
    * Constructs a Ship with durability, damage range, and initial upgrades.
    * Health points are initialized to the durability value.
    * 
    * @param durability  the maximum and initial health of the ship
    * @param damageRange array with minimum and maximum damage values
    * @param upgrades    array of upgrades to be installed on the ship
    * @author Cooper Lauer
    * @date 4/20/26
    */
   public Ship(String name, int[] coords, int durability, int[] damageRange, String[] upgrades) {
      super(name, coords);
      this.durability = durability;
      this.baseDurability = durability;
      hp = durability;
      this.damageRange = damageRange;
      this.baseDamageRange = damageRange;
      this.upgrades = upgrades;
   }

   /**
    * Gets the current health points of the ship.
    * 
    * @return the current HP
    * @author Cooper Lauer
    * @date 4/20/26
    */
   public int getHP() {
      return hp;
   }

   /**
    * Sets the current health points of the ship.
    * 
    * @param hp the new health point value
    * @author Cooper Lauer
    * @date 4/20/26
    */
   public void setHP(int hp) {
      this.hp = hp;
   }

   /**
    * Reduces the ship's health points by the specified damage amount.
    * 
    * @param damage the amount of damage to take
    * @author Cooper Lauer
    * @date 4/20/26
    */
   public void takeDamage(int damage) {
      hp -= damage;
   }

   /**
    * Gets the damage range of this ship.
    * 
    * @return array containing minimum (index 0) and maximum (index 1) damage
    *         values
    * @author Cooper Lauer
    * @date 4/20/26
    */
   public int[] getDamageRange() {
      return damageRange;
   }

   /**
    * Sets the damage range of this ship.
    * 
    * @param damageRange array containing minimum (index 0) and maximum (index 1)
    *                    damage values
    * @author Cooper Lauer
    * @date 4/20/26
    */
   public void setDamageRange(int[] damageRange) {
      this.damageRange = damageRange;
   }

   /**
    * Attacks another ship, dealing random damage within the ship's damage range.
    * The damage dealt is a random value between damageRange[0] and damageRange[1].
    * 
    * @param target the Ship to attack
    * @author Cooper Lauer
    * @date 4/20/26
    */
   public void attack(Ship target) {
      int damage = rand.nextInt(damageRange[1] - damageRange[0]) + damageRange[0];
      target.takeDamage(damage);
   }

   /**
    * Moves the ship forward (decreasing Y coordinate, wrapping around if needed).
    * @author Cooper Lauer
    * @date 4/25/26
    */
   public void moveForward() {
      int newY;
      if (super.coords[1] - 1 >= 0) {
         newY = super.coords[1] - 1;
      } else {
         newY = Universe.getBoundsY() - 1;
      }

      Universe.setObject(new int[]{super.coords[0], newY}, this);
   }

   /**
    * Moves the ship backward (increasing Y coordinate, wrapping around if needed).
    * @author Cooper Lauer
    * @date 4/25/26
    */
   public void moveBackward() {
      int newY;
      if (super.coords[1] + 1 < Universe.getBoundsY()) {
         newY = super.coords[1] + 1;
      } else {
         newY = 0;
      }

      Universe.setObject(new int[]{super.coords[0], newY}, this);
   }

   /**
    * Moves the ship left (decreasing X coordinate, wrapping around if needed).
    * @author Cooper Lauer
    * @date 4/25/26
    */
   public void moveLeft() {
      int newX;
      if (super.coords[0] - 1 >= 0) {
         newX = super.coords[0] - 1;
      } else {
         newX = Universe.getBoundsX() - 1;
      }

      Universe.setObject(new int[]{newX, super.coords[1]}, this);
   }

   /**
    * Moves the ship right (increasing X coordinate, wrapping around if needed).
    * @author Cooper Lauer
    * @date 4/25/26
    */
   public void moveRight() {
      int newX;
      if (super.coords[0] + 1 < Universe.getBoundsX()) {
         newX = super.coords[0] + 1;
      } else {
         newX = 0;
      }

      Universe.setObject(new int[]{newX, super.coords[1]}, this);
   }

   /**
    * Installs an upgrade in a specific slot on this ship.
    * Each slot can hold one upgrade. Installing a new upgrade will overwrite any
    * existing upgrade in that slot.
    * Valid slots are 0, 1, and 2.
    * 
    * @param upgrade the name of the upgrade to install
    * @param slot    the slot index (0, 1, or 2) where the upgrade should be
    *                installed
    * @author Cooper Lauer
    * @date 4/19/26
    */
   public void installUpgrade(String upgrade, int slot) {
      upgrades[slot] = upgrade;
   }

}

/**
 * An assault variant of the Ship with increased damage but lower durability.
 * AssaultShips have 8 durability and deal 3-5 damage per attack.
 * @author Cooper Lauer
 * @date 4/25/26
 */
class AssaultShip extends Ship {

   /**
    * Constructs a new AssaultShip with combat-focused stats.
    * Initializes with 8 durability and damage range of 3-5.
    * @author Cooper Lauer
    * @date 4/25/26
    */
   public AssaultShip(String name, int[] coords) {
      super(name, coords, 8, new int[]{3, 5});
   }
}

/**
 * A heavy variant of the Ship with increased durability but lower damage.
 * HeavyShips have 15 durability and deal 1-3 damage per attack.
 * @author Cooper Lauer
 * @date 4/25/26
 */
class HeavyShip extends Ship {

   /**
    * Constructs a new HeavyShip with defensive-focused stats.
    * Initializes with 15 durability and damage range of 1-3.
    * @author Cooper Lauer
    * @date 4/25/26
    */
   public HeavyShip(String name, int[] coords) {
      super(name, coords, 15, new int[]{1, 3});
   }
}
