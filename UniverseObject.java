public class UniverseObject {
    int x;
    int y;
    public UniverseObject() {
        this.x = 0;
        this.y= 0;
        Universe.setObject(0, 0, this);
    }
}