package app.service;

import app.model.ranking.personal.RankStep;
import app.model.ranking.personal.RankedObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PersonalRanking {

    private static final Logger log = LoggerFactory.getLogger(PersonalRanking.class);

    // 2nd method of the personal ranking
    public static List<RankStep> RankBySecondMethod(List<Float> initial) {
        log.debug("ENTER");
        List<RankStep> steps = new ArrayList<>();
        List<RankedObject> result = new ArrayList<>();
        int stepNumber = 1;
        int comparisonsCount = 0;

        result.add(new RankedObject(0, initial.get(0)));
        steps.add(new RankStep(stepNumber, 0, new ArrayList<>(result)));
        stepNumber++;

        float currentElementWeight = 0;
        for (int i = 1; i < initial.size(); i++) {
            comparisonsCount = 0;
            currentElementWeight = initial.get(i);

            for (int j = 0; j < result.size(); j++) {
                if (currentElementWeight > result.get(j).getWeight()) {
                    result.add(j, new RankedObject(i, currentElementWeight));
                    comparisonsCount++;
                    break;
                }
                comparisonsCount++;
            }

            if (result.size() != stepNumber) {
                result.add(new RankedObject(i, currentElementWeight));
            }

            steps.add(new RankStep(stepNumber, comparisonsCount, new ArrayList<>(result)));
            log.debug("result, step {} = {}", stepNumber, result);
            stepNumber++;
        }


        log.debug("result = {}", result);
        log.debug("steps = {}", steps);
        log.debug("EXIT");
        return steps;
    }
}
