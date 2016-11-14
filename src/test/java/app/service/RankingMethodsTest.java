package app.service;

import app.Application;
import app.model.ranking.group.BordRankingResult;
import app.model.ranking.personal.RankStep;
import app.model.ranking.personal.RankedObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RankingMethodsTest {

    @Test(expected = IllegalArgumentException.class)
    public void rankBySecondMethodTest() throws Exception {
        List<Float> weights = new ArrayList<>();
        weights.add(1f);
        weights.add(2f);
        weights.add(4f);
        weights.add(5f);
        weights.add(3f);

        List<RankStep> steps = RankingMethods.RankBySecondMethod(weights);
        assertEquals(steps.size(), 5);
        assertEquals(steps.get(0).getRankedObjectList().size(), 1);
        assertEquals(steps.get(0).getStepNumber(), 1);
        assertEquals(steps.get(steps.size() - 1).getStepNumber(), 5);

        Collections.sort(weights);
        Collections.reverse(weights);
        List<Float> resultWeights = new ArrayList<>();
        for (RankedObject rankedObject : steps.get(steps.size() - 1).getRankedObjectList()) {
            resultWeights.add(rankedObject.getWeight());
        }
        assertEquals(weights, resultWeights);
        RankingMethods.RankBySecondMethod(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rankByBordMethodTest() throws Exception {
        RankingMethods.RankByBordMethod(null);

        List<String> expertsRanking = new ArrayList<>();
        expertsRanking.add("0,>,1,~,2");
        expertsRanking.add("1,>,0,~,2");
        expertsRanking.add("1,~,2,~,0");
        expertsRanking.add("2,>,1,>,0");

        BordRankingResult bordRankingResult = RankingMethods.RankByBordMethod(expertsRanking);
        assertEquals(bordRankingResult.getCalculatedRanksByExpertRanking().size(), 4);
        assertArrayEquals(bordRankingResult.getCalculatedRanksByExpertRanking().get(0).toArray(), Arrays.asList(0f, 1f, 2f).toArray());
        assertArrayEquals(bordRankingResult.getCalculatedRanksByExpertRanking().get(1).toArray(), Arrays.asList(1.5f, 0,1.5f).toArray());
        assertArrayEquals(bordRankingResult.getCalculatedRanksByExpertRanking().get(2).toArray(), Arrays.asList(1f, 1f, 1f).toArray());
        assertArrayEquals(bordRankingResult.getCalculatedRanksByExpertRanking().get(3).toArray(), Arrays.asList(2f, 1f, 0f).toArray());

        assertEquals(bordRankingResult.getSumMarks().size(), 3);
        assertEquals(bordRankingResult.getSumMarks().get(0).getSumRank(), 4.5f);
        assertEquals(bordRankingResult.getSumMarks().get(1).getSumRank(), 3f);
        assertEquals(bordRankingResult.getSumMarks().get(2).getSumRank(), 4.5f);
        assertEquals(bordRankingResult.getResultRanking(), "a1 > a0 ~ a2");
    }

}
