import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// The main purpose of this class is currently to make containers up-and-down-arrow-key navigable to screen readers, and also to make JLabel (plain text on the screen) navigable to screen readers by default
// However, these changes will only affect widgets inside a container that has been modified via the modifyContainer method

public class Accessible {
	public static void modifyContainer(Container c) {
		JComponent jcomp = (JComponent) c; // Converts the container to a more generic class that has important functionality for the purposes of this method
		// The following two lines make it so that any widgets inside the modified container can be navigated with the up and down arrow keys via a screenreader
		bind(jcomp, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up", new RunnableAction(() -> KeyboardFocusManager.getCurrentKeyboardFocusManager().focusPreviousComponent()));
		bind(jcomp, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down", new RunnableAction(() -> KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent()));
		// The following code tracks whenever a widget is added to the modified container and makes it focusable / navigable to screen readers, which mostly just affects JLabels (plain text widgets) which are not focusable by default
		c.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent evt) {
				Component child = evt.getChild();
				child.setFocusable(true);
			}
		});
	}
	// A method that makes adding keyboard event listeners easier
	public static void bind(JComponent c, KeyStroke keyStroke, String actionName, RunnableAction action) {
		c.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, actionName);
		c.getActionMap().put(actionName, action);
	}
	// I seem to have left out the method that unbinds a given binding, but for now it's not needed and may never be anyway
}
// The following is an event class used above that allows any given function/method to be used as the argument/action to be performed when the event triggers
class RunnableAction extends AbstractAction {
	private Runnable action; // A special java object for containing functions
	public RunnableAction(Runnable a) {
		action = a;
	}
	public void actionPerformed(ActionEvent evt) {
		action.run(); // Runs the function when the event fires
}
}
