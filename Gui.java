import javax.swing.*; // GUI library

import java.awt.FocusTraversalPolicy;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.border.Border;
// Use the print method to add text from anywhere

// This is a begaining page when a user launches the program they will land on this page 1st like welcoming message (Theo)
public class Gui {
      static JFrame frame; // The variable that will contain the interface window
      static JPanel panel; // A container to put GUI widgets in
      static JPanel CurrentWindow;
      static FocusTraversalPolicy policy;
      public static void setGUI() {
            frame = new JFrame("Space Pirates Beta");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel = new JPanel();
            panel.setFocusCycleRoot(true);
            CurrentWindow = panel;
            frame.add(panel);
            frame.setSize(300, 300);
                        frame.setVisible(true);
      }

      public static JPanel createMenu(JComponent ...comps) {
            JPanel panel = new JPanel();
            Menu menu = new Menu();
            for (JComponent comp : comps) {
                  if (comp instanceof JLabel) {
                        comp.setFocusable(true);
                  }
            menu.add(comp);
      }
            panel.add(menu);
            frame.add(panel);
            panel.setFocusCycleRoot(true);
            panel.setVisible(false);
      return panel;
      }
      public static void openMenu(JPanel menu) {
            CurrentWindow.setVisible(false);
            CurrentWindow = menu;
            CurrentWindow.setVisible(true);
            FocusTraversalPolicy policy = CurrentWindow.getFocusTraversalPolicy();
            policy.getFirstComponent(CurrentWindow).requestFocusInWindow();
      }

            public static void closeMenu() {
            CurrentWindow.setVisible(false);
            CurrentWindow = panel;
            CurrentWindow.setVisible(true);
            CurrentWindow.requestFocusInWindow();
      }

      // The following are overloads for a method for binding actions to keys
      // Example: addBinding(panel, "bind_key_c", "C", e -> System.out.println(e.getWhen()));
      // "bind_key_c" is just an identifier for swing to track bindings, and it is important.
      // "C" is the key that the action is bound to (the lambda in this case, which prints a millisecond timestamp of when the key was pressed)
      // You can also make a no-argument lambda if you don't need to make use of any ActionEvent properties or methods
      // For the key stroke, you can provide a KeyStroke object yourself or just put a string
      // The string can have letters (capitolized), keys like Tab or Space (I believe with the first letter capitolized, I am not completely certain of the rules here), and modifiers case-insensative, which support modern abreviations for things like control but also the proper spelling
      // Example: You can replace "C" in the exampel above with "control C", "ctrl C", and so forth to have the action bound to control + C
      // You can also provide your own Action object, but this method also supports lambdas as shown above

      public static void addBinding(JComponent window, String name, String keyStroke, Consumer<ActionEvent> action) {
            addBinding(window, name, KeyStroke.getKeyStroke(keyStroke), action);
      }

      public static void addBinding(JComponent window, String name, String keyStroke, Runnable action) {
            addBinding(window, name, KeyStroke.getKeyStroke(keyStroke), action);
      }

      public static void addBinding(JComponent window, String name, KeyStroke keyStroke, Runnable action) {
            window.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, name);
            window.getActionMap().put(name, new Binding(action));
      }

      public static void addBinding(JComponent window, String name, KeyStroke keyStroke, Consumer<ActionEvent> action) {
            window.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, name);
            window.getActionMap().put(name, new Binding(action));
      }

      // A function that makes any active screen reader speak the given text
      public static void print(String text) {
            Jsrol.output(text);
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

class Binding extends AbstractAction {
      Runnable runnable = null;
      Consumer<ActionEvent> consumer = null;
      public Binding(Runnable r) {
            runnable = r;
      }
      public Binding(Consumer<ActionEvent> c) {
            consumer = c;
      }
      public void actionPerformed(ActionEvent e) {
            if (runnable != null) runnable.run();
            else if (consumer != null) consumer.accept(e);
      }
}