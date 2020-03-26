package sample;

public class InsertValue {
    private int value;
    private int position;

    public InsertValue(int value, int position) {
        this.value = value;
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
