import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Menu extends JToolBar {
    private int lastFocusedIndex = -1;
    private boolean isNavigatingInternally = false;

    public Menu() {
        setOrientation(JToolBar.VERTICAL);
        setFloatable(false);
        setBorderPainted(false);

        // 1. CRITICAL: The toolbar container itself must NOT be focusable.
        // This eliminates the empty container focus box completely.
        setFocusable(false);

        // 2. TRAP THE TAB KEY: Override the layout's traversal policy.
        // To the window's Tab layout, this whole Menu looks like a single invisible widget stop.
        setFocusTraversalPolicyProvider(true);
        setFocusTraversalPolicy(new FocusTraversalPolicy() {
            @Override
            public Component getComponentAfter(Container c, Component comp) {
                isNavigatingInternally = false; // Left the group
                return null; // Forces Tab to exit the entire Menu block
            }
            @Override
            public Component getComponentBefore(Container c, Component comp) {
                isNavigatingInternally = false; // Left the group
                return null; // Forces Shift+Tab to exit the entire Menu block
            }
            @Override
            public Component getFirstComponent(Container c) {
                // When Tab/Shift+Tab enters the Menu from outside, land on the correct child
                int target = (lastFocusedIndex >= 0 && lastFocusedIndex < getComponentCount()) ? lastFocusedIndex : 0;
                return getComponentCount() > 0 ? getComponent(target) : null;
            }
            @Override public Component getLastComponent(Container c) { return getFirstComponent(c); }
            @Override public Component getDefaultComponent(Container c) { return getFirstComponent(c); }
        });

        // 3. DISABLE LEFT/RIGHT NAVIGATION AT THE SOURCE.
        // Swing's BasicToolBarUI binds arrow-key navigation ("navigateUp", "navigateDown",
        // "navigateLeft", "navigateRight") at the WHEN_ANCESTOR_OF_FOCUSED_COMPONENT level on
        // the toolbar itself, not WHEN_FOCUSED on the children. Rebinding a child's own
        // WHEN_FOCUSED map to a non-existent action name (like the old "none" string with no
        // matching Action) doesn't consume the key event -- it just means "no binding found",
        // so the event falls through to the toolbar's ancestor-level binding anyway, which is
        // why Left/Right kept working. Overriding the toolbar's own ActionMap entries for
        // "navigateLeft"/"navigateRight" with real no-op Actions disables them at the source,
        // regardless of which child has focus.
        Action noOpHorizontalNav = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Intentionally does nothing; this consumes Left/Right so they have no effect.
            }
        };
        ActionMap toolbarActionMap = getActionMap();
        toolbarActionMap.put("navigateRight", noOpHorizontalNav);
        toolbarActionMap.put("navigateLeft", noOpHorizontalNav);
    }

    @Override
    protected void addImpl(Component comp, Object constraints, int index) {
        super.addImpl(comp, constraints, index);

        // Keep children focusable so Arrow Keys and text input always work perfectly
        comp.setFocusable(true);

        if (comp instanceof JComponent jComp) {
            InputMap im = jComp.getInputMap(JComponent.WHEN_FOCUSED);
            ActionMap am = jComp.getActionMap();

            int currentChildIndex = getComponentCount() - 1;

            jComp.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    AccessibleContext childCtx = jComp.getAccessibleContext();

                    Component opposite = e.getOppositeComponent();
                    isNavigatingInternally = !(opposite == null || !SwingUtilities.isDescendingFrom(opposite, Menu.this));

                    // Use the DESCRIPTION, not the NAME, to convey "you're in a menu". The
                    // accessible name is what screen readers treat as the object's core label --
                    // it's what gets pulled into character-by-character spelling, braille, and
                    // word review, so prefixing it there polluted those experiences. Description
                    // is a separate, secondary property: normally still spoken on focus (NVDA's
                    // "report object descriptions" is on by default) without being treated as
                    // reviewable label text. The item's real accessible name is left untouched.
                    childCtx.setAccessibleDescription("Menu");

                    lastFocusedIndex = currentChildIndex;
                }
            });

            // Bind Arrow Down
            im.put(KeyStroke.getKeyStroke("DOWN"), "navigateDown");
            am.put("navigateDown", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    navigate(1);
                }
            });

            // Bind Arrow Up
            im.put(KeyStroke.getKeyStroke("UP"), "navigateUp");
            am.put("navigateUp", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    navigate(-1);
                }
            });
        }
    }

    private void navigate(int direction) {
        int count = getComponentCount();
        if (count == 0) return;

        // Loop array boundaries safely up and down
        int nextIndex = (lastFocusedIndex + direction + count) % count;

        isNavigatingInternally = true;
        getComponent(nextIndex).requestFocusInWindow();
    }

    // Intercept screen reader role evaluations dynamically
    @Override
    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) {
            accessibleContext = new AdaptiveAccessibleMenu();
        }
        return accessibleContext;
    }

    protected class AdaptiveAccessibleMenu extends JToolBar.AccessibleJToolBar {
        @Override
        public AccessibleRole getAccessibleRole() {
            // Always report MENU_BAR, even during internal Up/Down navigation. Screen-reader
            // "report current focus" commands (e.g. NVDA+Tab) walk the ancestor chain at query
            // time to build their context breadcrumb ("menu, Item Name"). If this role flips to
            // PANEL while arrowing internally, such a query mid-navigation finds no menu context
            // at all.
            return AccessibleRole.MENU_BAR;
        }
    }
}