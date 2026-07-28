public class UniverseObject {
    String name;
    int[] coords; // coordinates with index 0 = x and index 1 = y
    public UniverseObject(String name, int[] c) {
        this.name = name;
        this.coords = c;
        Universe.setObject(c, this);
    }

    public String getName () {
return name;
    }

    public int[] getCoords () {
return coords;
    }

}