// TODO
// Download and setup SRAL for screen reader output
// This is the best way to manually send text to screen readers alone

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// The main purpose of this class is currently to make containers up-and-down-arrow-key navigable to screen readers, and also to make JLabel (plain text on the screen) navigable to screen readers by default
// However, these changes will only affect widgets inside a container that has been modified via the modifyContainer method

public class Accessible {
	static boolean firstComponent; // A boolean to determine whether the component being added to a container is the first the Accessible class has encountered or not
	public static void modifyContainer(Container c) {
		if (!(c instanceof JComponent))
			return;
		c.setFocusCycleRoot(true);
		c.setFocusTraversalPolicy(new DefaultFocusTraversalPolicy());
		JComponent jcomp = (JComponent) c; // Converts the container to a more generic class that has important
											// functionality for the purposes of this method
		// The following lines make it so that any widgets inside the modified container
		// can be navigated with the up and down arrow keys via a screenreader
		bind(jcomp, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "GO_TO_PREVIOUS", new MoveFocus(0)); // Move to previous element on up arrow key
		bind(jcomp, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "GO_TO_NEXT", new MoveFocus(1)); // Move to next element on down arrow key
		bind(jcomp, KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 0), "GO_TO_FIRST", new MoveFocus(2)); // Move to first element on left arrow key
		bind(jcomp, KeyStroke.getKeyStroke(KeyEvent.VK_END, 0), "GO_TO_LAST", new MoveFocus(3)); // Move to last element on right arrow key
		// Make all already-present children focusable first
		for (Component child : c.getComponents()) {
			child.setFocusable(true);
		}
		// The following code tracks whenever a widget is added to the modified
		// container and makes it focusable / navigable to screen readers, which mostly
		// just affects JLabels (plain text widgets) which are not focusable by default
		c.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent evt) {
				Component child = evt.getChild();
				child.setFocusable(true);
				if (firstComponent == false) {
					child.requestFocusInWindow();
					firstComponent = true;
				}
			}
		});
	}

	// A method to make screen readers repeat the currently selected component
	public static void repeat() {
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		Component focus = manager.getFocusOwner();
		if (focus != null) {
			SwingUtilities.invokeLater(() -> {
				focus.getParent().requestFocusInWindow();
				focus.requestFocusInWindow();
			});
		}
	}

	// A method to announce text to screen readers using the live region
	static void announce(String text) {
	}

	// A method that makes adding keyboard event listeners easier
	public static void bind(JComponent c, KeyStroke keyStroke, String actionName, MoveFocus action) {
		c.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(keyStroke, actionName);
		c.getActionMap().put(actionName, action);
	}
	// I seem to have left out the method that unbinds a given binding, but for now
	// it's not needed and may never be anyway
}

// The following is an event class used above that allows any given
// function/method to be used as the argument/action to be performed when the
// event triggers
class MoveFocus extends AbstractAction {
	int dir;

	public MoveFocus(int dir) {
		this.dir = dir;
	}

	public void actionPerformed(ActionEvent evt) {
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		Component focus = manager.getFocusOwner();
		if (focus != null) {
			Container root = focus.getParent();
			if (root == null)
				return;
			FocusTraversalPolicy policy = root.getFocusTraversalPolicy();
			if (policy == null)
				return;
			if (dir == 0) {
				if (focus.equals(policy.getFirstComponent(root))) {
					Accessible.repeat();
					Toolkit.getDefaultToolkit().beep();
				} else {
					manager.focusPreviousComponent();
				}
			} else if (dir == 1) {
				if (focus.equals(policy.getLastComponent(root))) {
					Accessible.repeat();
					Toolkit.getDefaultToolkit().beep();
				} else {
					manager.focusNextComponent();
				}
			} else if (dir == 2) {
				if (focus.equals(policy.getFirstComponent(root))) {
					Accessible.repeat();
					Toolkit.getDefaultToolkit().beep();
				} else {
					policy.getFirstComponent(root).requestFocusInWindow();
				}
			} else if (dir == 3) {
				if (focus.equals(policy.getLastComponent(root))) {
					Accessible.repeat();
					Toolkit.getDefaultToolkit().beep();
				} else {
					policy.getLastComponent(root).requestFocusInWindow();
				}
			} 

		}
	}
}