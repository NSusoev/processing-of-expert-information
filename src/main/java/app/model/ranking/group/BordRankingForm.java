package app.model.ranking.group;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BordRankingForm {

    @NotNull
    @Min(1)
    private Integer expertsCount;
    @NotNull
    @Min(1)
    private Integer objectsCount;

    public Integer getExpertsCount() {
        return expertsCount;
    }

    public void setExpertsCount(Integer expertsCount) {
        this.expertsCount = expertsCount;
    }

    public Integer getObjectsCount() {
        return objectsCount;
    }

    public void setObjectsCount(Integer objectsCount) {
        this.objectsCount = objectsCount;
    }

    @Override
    public String toString() {
        return "BordRankingForm{" +
                "expertsCount=" + expertsCount +
                ", objectsCount=" + objectsCount +
                '}';
    }
}
