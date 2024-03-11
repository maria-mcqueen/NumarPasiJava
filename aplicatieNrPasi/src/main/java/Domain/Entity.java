package Domain;

public abstract class Entity {
    protected int data;

    public Entity(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
}
