package app.model.ranking.group;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class BordRankingForm {

    @NotNull
    @Min(1)
    private Integer expertsCount;
    @NotNull
    @Min(1)
    private Integer objectsCount;
    List<String> personalRankings;

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

    public List<String> getPersonalRankings() {
        return personalRankings;
    }

    public void setPersonalRankings(List<String> personalRankings) {
        this.personalRankings = personalRankings;
    }

    @Override
    public String toString() {
        return "BordRankingForm{" +
                "expertsCount=" + expertsCount +
                ", objectsCount=" + objectsCount +
                ", personalRankings=" + personalRankings +
                '}';
    }
}
