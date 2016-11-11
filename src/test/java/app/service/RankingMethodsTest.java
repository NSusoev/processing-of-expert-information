package app.service;

import app.Application;
import app.model.ranking.personal.RankStep;
import app.model.ranking.personal.RankedObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class RankingMethodsTest {

    @Test
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
    }

}
