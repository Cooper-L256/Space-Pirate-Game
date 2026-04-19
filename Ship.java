/** 
 * Cooper Lauer 4/19/26
 * This class represents a ship in the game. It has a durability, currentHP, and can have up to 3 upgrades installed. The durability is set when the ship is created and cannot be changed. The currentHP starts at the durability and can be reduced when the ship takes damage. The upgrades are stored in an array of strings, where each slot can hold one upgrade. The installUpgrade method allows you to install an upgrade in a specific slot.
 * /
public class Ship {
 final int durability;
 int currentHP;
 String[] upgrades = new String[3];
 
 /** 
  * This constructor initializes the ship with a given durability. The currentHP is set to the durability, meaning the ship starts at full health. The upgrades array is initialized to hold up to 3 upgrades, but they are all null until an upgrade is installed.
  */
 public Ship (int durability) {
    this.durability = durability;
    this.currentHP = durability;
 }

    /** 
    * This method allows you to install an upgrade in a specific slot. The upgrade is represented as a string, and the slot is an integer index (0, 1, or 2). When you call this method, it will place the upgrade in the specified slot of the upgrades array. If there is already an upgrade in that slot, it will be overwritten.
    */
 public void installUpgrade (String upgrade, int slot) {
upgrades[slot] = upgrade;
 }
}