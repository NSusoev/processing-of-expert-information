package app.model.ranking.group;

import java.util.List;

public class BordRankingResult {

    private List<List<Float>> calculatedRanksByExpertRanking;
    private List<ObjectSumRank> sumMarks;
    private String resultRanking;

    public List<List<Float>> getCalculatedRanksByExpertRanking() {
        return calculatedRanksByExpertRanking;
    }

    public void setCalculatedRanksByExpertRanking(List<List<Float>> calculatedRanksByExpertRanking) {
        this.calculatedRanksByExpertRanking = calculatedRanksByExpertRanking;
    }

    public List<ObjectSumRank> getSumMarks() {
        return sumMarks;
    }

    public void setSumMarks(List<ObjectSumRank> sumMarks) {
        this.sumMarks = sumMarks;
    }

    public String getResultRanking() {
        return resultRanking;
    }

    public void setResultRanking(String resultRanking) {
        this.resultRanking = resultRanking;
    }

    @Override
    public String toString() {
        return "BordRankingResult{" +
                "ranksByExperts=" + calculatedRanksByExpertRanking +
                ", sumMarks=" + sumMarks +
                ", resultRanking='" + resultRanking + '\'' +
                '}';
    }
}
