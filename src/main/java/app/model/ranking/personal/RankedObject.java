package app.model.ranking.personal;

public class RankedObject {

    // object's index in initial list
    private int initialIndex;
    // object's weight
    private float weight;

    public RankedObject(int initialIndex, float weight) {
        this.initialIndex = initialIndex;
        this.weight = weight;
    }

    public int getInitialIndex() {
        return initialIndex;
    }

    public void setInitialIndex(int initialIndex) {
        this.initialIndex = initialIndex;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "RankedObject{" +
                "initialIndex=" + initialIndex +
                ", weight=" + weight +
                '}';
    }
}
