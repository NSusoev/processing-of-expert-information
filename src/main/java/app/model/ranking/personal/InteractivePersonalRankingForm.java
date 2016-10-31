package app.model.ranking.personal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class InteractivePersonalRankingForm {

    private List<Integer> sortedPart;
    private Integer insertNewBeforeIndex;
    private Integer newObjectNumber;
    @NotNull
    @Min(1)
    private Integer objectsCount;

    public List<Integer> getSortedPart() {
        return sortedPart;
    }

    public void setSortedPart(List<Integer> sortedPart) {
        this.sortedPart = sortedPart;
    }

    public Integer getInsertNewBeforeIndex() {
        return insertNewBeforeIndex;
    }

    public void setInsertNewBeforeIndex(Integer insertNewBeforeIndex) {
        this.insertNewBeforeIndex = insertNewBeforeIndex;
    }

    public Integer getNewObjectNumber() {
        return newObjectNumber;
    }

    public void setNewObjectNumber(Integer newObjectNumber) {
        this.newObjectNumber = newObjectNumber;
    }

    public Integer getObjectsCount() {
        return objectsCount;
    }

    public void setObjectsCount(Integer objectsCount) {
        this.objectsCount = objectsCount;
    }

    @Override
    public String toString() {
        return "InteractivePersonalRankingForm{" +
                "sortedPart=" + sortedPart +
                ", insertNewBeforeIndex=" + insertNewBeforeIndex +
                ", newObjectNumber=" + newObjectNumber +
                ", objectsCount=" + objectsCount +
                '}';
    }
}
