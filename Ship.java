import java.util.ArrayList;
import java.util.Random;

public class Ship extends UniverseObject {
   int durability;
   int targetRange;
   int[] damageRange = new int[2];
   int hp;
   String[] upgrades = new String[3];

   Random rand = new Random();

   public Ship(String name, int[] coords, int durability, int targetRange, int[] damageRange) {
      super(name, coords);
      this.durability = durability;
      hp = durability;
      this.targetRange = targetRange;
      this.damageRange = damageRange;
   }

   public int getHP() {
      return hp;
   }

   public void setHP(int hp) {
      this.hp = hp;
   }

   public void takeDamage(int damage) {
      hp -= damage;
   }

   public int getTargetRange () {
      return targetRange;
   }

   public void setTargetRange (int targetRange) {
      this.targetRange = targetRange;
   }

   public int[] getDamageRange() {
      return damageRange;
   }

   public void setDamageRange(int[] damageRange) {
      this.damageRange = damageRange;
   }

public ArrayList<Ship> getAvailableTargets () {
ArrayList<Ship> targetableShips = new ArrayList<Ship>();



return targetableShips;
}

   public void attack(Ship target) {
      int damage = rand.nextInt(damageRange[1] - damageRange[0]) + damageRange[0];
      target.takeDamage(damage);
   }

   public void moveForward() {
      int newY;
      if (super.coords[1] - 1 >= 0) {
         newY = super.coords[1] - 1;
      } else {
         newY = Universe.getBoundsY() - 1;
      }

      Universe.setObject(new int[]{super.coords[0], newY}, this);
      Universe.removeObject(new int[]{super.coords[0], super.coords[1]});

      super.coords[1] = newY;
   }

   public void moveBackward() {
      int newY;
      if (super.coords[1] + 1 < Universe.getBoundsY()) {
         newY = super.coords[1] + 1;
      } else {
         newY = 0;
      }

      Universe.setObject(new int[]{super.coords[0], newY}, this);
      Universe.removeObject(new int[]{super.coords[0], super.coords[1]});

      super.coords[1] = newY;
   }

   public void moveLeft() {
      int newX;
      if (super.coords[0] - 1 >= 0) {
         newX = super.coords[0] - 1;
      } else {
         newX = Universe.getBoundsX() - 1;
      }

      Universe.setObject(new int[]{newX, super.coords[1]}, this);
      Universe.removeObject(new int[]{super.coords[0], super.coords[1]});

      super.coords[0] = newX;
   }

   public void moveRight() {
      int newX;
      if (super.coords[0] + 1 < Universe.getBoundsX()) {
         newX = super.coords[0] + 1;
      } else {
         newX = 0;
      }

      Universe.setObject(new int[]{newX, super.coords[1]}, this);
      Universe.removeObject(new int[]{super.coords[0], super.coords[1]});

      super.coords[0] = newX;
   }
   
   public void installUpgrade(String upgrade, int slot) {
      upgrades[slot] = upgrade;
   }

}