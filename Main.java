import javax.swing.*; // GUI library

// Use the print method to add text from anywhere

// This is a begaining page when a user launches the program they will land on this page 1st like welcoming message (Theo)
public class Main {
      static JFrame frame; // The variable that will contain the interface window
      static JPanel panel; // A container to put GUI widgets in

      private static void setGUI() {
            frame = new JFrame("Space Pirates Beta");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel = new JPanel();
            Accessible.modifyContainer(panel); // Makes the panel more accessible, see Accessible.java
            frame.add(panel);
            frame.setSize(300, 300);
            frame.setVisible(true);

      }

      private static void print(String text) {
            panel.add(new JLabel(text));
      }

      public static void main(String[] args) {
            setGUI(); // Sets up the window
            // Create a ship
            Ship ship = new Ship("Ship 1", new int[]{0, 0}, 10, new int[] { 5, 10 });
            print(ship.name+" is currently at coordinates (" + ship.coords[0] + ", " + ship.coords[1] + ")");
            // This next line was moved from outside the main method to inside where it
            // belongs
            game_message(); // a classs being called
      }

      // This class prints the welcome statment and greeds thw players though I don't
      // know how to controle when to display this message. (theo)

      /// Q ESTIONS TIP SUPPORT: WHAT DOSE THE ARG MEAN WHEN IN A PARENTHESES DELEATE
      /// THIS ONE YOU ANSWER ON TEXT OR CALL
      /// Kaleb Edited to be a method, not a class, as the contents was designed to be
      /// a method even though it was created as if it were a class
      /// Removed the String[] arg argument as it was written due to Theo's confusion
      /// on how these thigns work and had no function
      public static void game_message() {
            print(
                        "Welcome to the game named FIND MY WAY TO FREEDOM, a game that requires you to think outside of the box. Every choice you make could matter. There is no room for mistakes. The survival of your crew is in your hands. Can you prove you are the Champion to save your people?");

            print(
                        "The earth has been destroyed by the governments around the world. The 7 most powerful forces came together and fought the most devastating war. According to estimates, nearly 7.5 billion people have been wiped out from the face of the planet. The other 1 billion were annihilated from poisoning. The major forces that contributed to the war were China, Israel, the United States, Russia, Iran, India, and North Korea. This war could be described as World War III. It truly has been unleashed on the earth and has killed over 8 billion people. You and your friend happen to find a mysterious way to escape this planet to find freedom somewhere else. Earth is not the same as it was before.");
            print(
                        "The earth noew is down to complealy emptied. The forests are gone. Where ancient redwoods once stood, only blackened stumps remain, petrified by heat so intense that the wood fused with the soil beneath it. The Amazon, once the lungs of the planet, is a scorched wasteland of gray ash that stretches farther than the eye can see. No birdsong echoes through these dead places. No insects crawl beneath the bark, because there is no bark, and there are no insects. The great plains of wheat, corn, and rice that once fed billions are cracked expanses of poisoned earth, the soil so saturated with toxins that nothing will grow there for a thousand years.");
            print(
                        "The oceans have turned black. Coral reefs that took millions of years to build collapsed within weeks, bleached first by rising temperatures and then shattered by the shockwaves of underwater detonations. Whales wash ashore by the hundreds, their bodies covered in chemical burns. The fish that remain float belly-up in dead zones that span entire seas. Seabirds, the few that survived the initial blasts, fall from the sky mid-flight as their lungs fail.");
            print(
                        "Livestock has vanished entirely. The great cattle ranches of the Americas, the sheep pastures of New Zealand, the pig farms of Europe, all reduced to bone fields. Herds that once numbered in the millions now exist only as scattered skeletons half buried in toxic dust. Domestic dogs and cats, the companions of humanity for thousands of years, starved in abandoned cities or succumbed to the same poisons that killed their owners. Somewhere in the ruins, a few feral survivors still wander, mutated and desperate, but they are not the creatures you remember The cities are tombs. Skyscrapers in New York, Tokyo, Shanghai, and London stand half collapsed, their steel skeletons twisted into grotesque shapes by the firestorms that followed the first strikes. Highways stretch empty for thousands of miles, choked with the rusted husks of vehicles whose drivers never made it home. Traffic lights still blink in some places, powered by failing solar cells, casting red and green shadows across streets where nothing moves. Libraries, museums, cathedrals, and concert halls, the monuments of human civilization, are silent ruins filled with ash");
      }
}