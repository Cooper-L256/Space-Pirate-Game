import javax.swing.*;

public class Main {
public static void main(String[] args) {
            Gui.setGUI(); // Sets up the window
            // Create a ship
            Ship ship = new Ship("Ship 1", new int[]{0, 0}, 10, new int[] { 5, 10 });
            JPanel menu = Gui.createMenu(new JLabel("Hello. Welcome to Space Game, a very creative name for a game that has barely begun."), new JButton("Start Game"), new JButton("Settings"));
            Gui.openMenu(menu);
            // Test binding code
            Gui.addBinding(menu, "close_menu", "C", () -> Gui.closeMenu());
            Gui.addBinding(Gui.panel, "open_menu", "O", () -> Gui.openMenu(menu));

      }
	}
