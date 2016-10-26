package app.service;

import app.model.ranking.group.BordRankingResult;
import app.model.ranking.group.ObjectSumRank;
import app.model.ranking.group.Relation;
import app.model.ranking.personal.RankStep;
import app.model.ranking.personal.RankedObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankingMethods {

    private static final Logger log = LoggerFactory.getLogger(RankingMethods.class);

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

    public static BordRankingResult RankByBordMethod(List<String> expertsRanking) {
        log.debug("ENTER");
        BordRankingResult result = new BordRankingResult();
        List<ObjectSumRank> sumRanks = new ArrayList<>();
        List<List<Float>> calculatedRanks = new ArrayList<>();
        for (int i = 0; i < expertsRanking.size(); i++) {
            calculatedRanks.add(new ArrayList<>(Collections.nCopies(expertsRanking.get(i).length() / 2 + 1, 0f)));
        }

        for (int i = 0; i < expertsRanking.size(); i++) {
            int elemIndex = 0;
            String currentIndividualRanking = expertsRanking.get(i);

            while (elemIndex <= currentIndividualRanking.length() - 1) {
                if (elemIndex + 1 <= currentIndividualRanking.length() - 1) {
                    if (currentIndividualRanking.charAt(elemIndex + 1) == Relation.BIGGER.toString().charAt(0)) {
                        // TODO: обработать исключение
                        calculatedRanks.get(i).set(Character
                                .getNumericValue(currentIndividualRanking.charAt(elemIndex)), (float)elemIndex / 2);
                        elemIndex += 2;
                    } else {
                        int nextEqualRelationOperatorIndex = elemIndex + 1;
                        int equalElementsQueueSize = 1;
                        int firstEqualQueueElementIndex = nextEqualRelationOperatorIndex - 1;
                        int lastEqualQueueElementIndex;
                        int sumOfEqualElementsRanks = 0;
                        float equalElementsRank;

                        while (nextEqualRelationOperatorIndex <= currentIndividualRanking.length() - 1
                                && currentIndividualRanking
                                .charAt(nextEqualRelationOperatorIndex) == Relation.EQUAL.toString().charAt(0)) {
                            sumOfEqualElementsRanks += (nextEqualRelationOperatorIndex - 1) / 2;
                            nextEqualRelationOperatorIndex += 2;
                            equalElementsQueueSize++;

                            if (nextEqualRelationOperatorIndex + 1 > currentIndividualRanking.length() - 1
                                    || currentIndividualRanking.charAt(nextEqualRelationOperatorIndex) != Relation.EQUAL.toString().charAt(0)) {
                                sumOfEqualElementsRanks += (nextEqualRelationOperatorIndex - 1) / 2;
                            }
                        }
                        lastEqualQueueElementIndex = nextEqualRelationOperatorIndex;
                        equalElementsRank = (float)sumOfEqualElementsRanks / (float)equalElementsQueueSize;

                        for (int equalElemIndex = firstEqualQueueElementIndex;
                             equalElemIndex <= lastEqualQueueElementIndex; equalElemIndex += 2) {
                            // TODO: обработать исключение
                            calculatedRanks.get(i).set(Character.getNumericValue(currentIndividualRanking
                                    .charAt(equalElemIndex)), equalElementsRank);
                        }
                        elemIndex += equalElementsQueueSize * 2;
                    }
                } else {
                    // TODO: обработать исключение
                    calculatedRanks.get(i).set(Character
                            .getNumericValue(currentIndividualRanking.charAt(elemIndex)), (float)elemIndex / 2);
                    elemIndex++;
                }
            }
        }
        log.debug("calculated ranks = {}", calculatedRanks);
        result.setCalculatedRanksByExpertRanking(calculatedRanks);

        for (int i = 0; i < calculatedRanks.get(0).size(); i++) {
            float sumRank = 0;

            for (List<Float> calculatedRank : calculatedRanks) {
                sumRank += calculatedRank.get(i);
            }
            sumRanks.add(new ObjectSumRank(i, sumRank));
        }
        log.debug("sum marks = {}", sumRanks);
        result.setSumMarks(new ArrayList<>(sumRanks));

        Collections.sort(sumRanks);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sumRanks.size(); i++) {
            sb.append("a");
            sb.append(sumRanks.get(i).getObjectNumber());
            sb.append(" ");

            if (i + 1 < sumRanks.size() - 1 && sumRanks.get(i).getSumRank() == sumRanks.get(i + 1).getSumRank()) {
               sb.append("~ ");
            } else if (i < sumRanks.size() - 1) {
                sb.append("> ");
            }
        }
        log.debug("result ranking = {}", sb.toString());
        result.setResultRanking(sb.toString());
        log.debug("result = {}", result);
        log.debug("EXIT");
        return result;
    }

}
