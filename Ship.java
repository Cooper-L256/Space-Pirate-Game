import java.util.Random;

public class Ship extends UniverseObject {
   int durability;

   int[] damageRange = new int[2];

   int hp;

   String[] upgrades = new String[3];

   Random rand = new Random();

   public Ship(String name, int[] coords, int durability, int[] damageRange) {
      super(name, coords);
      this.durability = durability;
      hp = durability;
      this.damageRange = damageRange;
   }

   public Ship(String name, int[] coords, int durability, int[] damageRange, String[] upgrades) {
      super(name, coords);
      this.durability = durability;
      hp = durability;
      this.damageRange = damageRange;
      this.upgrades = upgrades;
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

   public int[] getDamageRange() {
      return damageRange;
   }

   public void setDamageRange(int[] damageRange) {
      this.damageRange = damageRange;
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
   }

   public void installUpgrade(String upgrade, int slot) {
      upgrades[slot] = upgrade;
   }

}