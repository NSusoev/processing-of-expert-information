package app.model.ranking.group;

public class ObjectSumRank implements Comparable {

    private int objectNumber;
    private float sumRank;

    public ObjectSumRank(int objectNumber, float sumRank) {
        this.objectNumber = objectNumber;
        this.sumRank = sumRank;
    }

    public int getObjectNumber() {
        return objectNumber;
    }

    public void setObjectNumber(int objectNumber) {
        this.objectNumber = objectNumber;
    }

    public float getSumRank() {
        return sumRank;
    }

    public void setSumRank(float sumRank) {
        this.sumRank = sumRank;
    }

    public int compareTo(Object obj) {
        ObjectSumRank objectSumRank = (ObjectSumRank) obj;

        if (objectSumRank.sumRank > this.sumRank) {
            return -1;
        } else if (objectSumRank.sumRank < this.sumRank) {
            return 1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "ObjectSumRank{" +
                "objectNumber=" + objectNumber +
                ", sumRank=" + sumRank +
                '}';
    }
}
