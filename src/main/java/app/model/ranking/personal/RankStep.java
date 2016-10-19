package app.model.ranking.personal;

import java.util.ArrayList;
import java.util.List;

public class RankStep {

    // ranking step's number
    private int stepNumber;
    // count of comparisons
    private int comparisonsCount;
    // state
    private List<RankedObject> rankedObjectList;

    public RankStep(int stepNumber) {
        this.stepNumber = stepNumber;
        rankedObjectList = new ArrayList<>();
    }

    public RankStep(int stepNumber, int comparisonsCount) {
        this.stepNumber = stepNumber;
        this.comparisonsCount = comparisonsCount;
        rankedObjectList = new ArrayList<>();
    }

    public RankStep(int stepNumber, int comparisonsCount, List<RankedObject> rankedObjectList) {
        this.stepNumber = stepNumber;
        this.comparisonsCount = comparisonsCount;
        this.rankedObjectList = rankedObjectList;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public int getComparisonsCount() {
        return comparisonsCount;
    }

    public void setComparisonsCount(int comparisonsCount) {
        this.comparisonsCount = comparisonsCount;
    }

    public List<RankedObject> getRankedObjectList() {
        return rankedObjectList;
    }

    public void setRankedObjectList(List<RankedObject> rankedObjectList) {
        this.rankedObjectList = rankedObjectList;
    }

    @Override
    public String toString() {
        return "\nRankStep{" +
                "stepNumber=" + stepNumber +
                ", comparisonsCount=" + comparisonsCount +
                ", rankedObjectList=" + rankedObjectList +
                "}";
    }
}
