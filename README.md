# Cooper, Kaleb, and Theo's Space Game

## About

This is a turn-based spaceship fighting and exploration game with a focus on blind accessibility.

## Compiling and Running
When compiling a file that uses jsrol.jar, run javac \@build.args \<file\>

Example:
```
javac @build.args Main.java
```
When running that same file, run java \@run.args \<class-name\>

Example:
```
java @run.args Main
```

### Menus
The following methods are part of Gui.java. Prefix the method name with Gui followed by a dot (.) if you are writing in a different class
* JPanel createMenu(JComponent ...comps): You can add any number of widgets by specifying them as an argument

Example: createMenu(new JButton("Test"));
The example here returns a panel with a single button labeled test. You can use it in the next method
* openMenu(JPanel menu): This hides the current window and makes the JPanel you provided visible
* closeMenu(): This hides the current window and makes visible the main panel

I am aware that this system is limited. It will definitely be worked on in future updates. These are just building blocks to go on for now.

## Adding Key Binds
The addBinding method  is part of Gui.java. Replace addBinding in the example below with Gui.addBinding if you are writing in a different class

Example: addBinding(panel, "bind_key_c", "C", e -> System.out.println(e.getWhen()));
"bind_key_c" is just an identifier for swing to track bindings, and it is important.
"C" is the key that the action is bound to (the lambda in this case, which prints a millisecond timestamp of when the key was pressed)
You can also make a no-argument lambda if you don't need to make use of any ActionEvent properties or methods
For the key stroke, you can provide a KeyStroke object yourself or just put a string
The string can have letters (capitolized), keys like Tab or Space (I believe with the first letter capitolized, I am not completely certain of the rules here), and modifiers case-insensative, which support modern abreviations for things like control but also the proper spelling
Example: You can replace "C" in the exampel above with "control C", "ctrl C", and so forth to have the action bound to control + C
You can also provide your own Action object, but this method also supports lambdas as shown above

## Credits

Cooper
Kaleb
Theo