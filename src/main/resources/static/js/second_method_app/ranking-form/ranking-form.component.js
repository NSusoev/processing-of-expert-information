angular.
  module('secondRankingMethodApp').
  component('rankingForm', {
    templateUrl: '/js/second_method_app/ranking-form/ranking-form.template.html',
    controller: ['$routeParams', function RankingFormController($routeParams) {
      this.objectsCount = parseInt($routeParams.objectsCount);
      this.newObjectInsertIndex = 0;
      this.rankingResult = [0];
      this.newObjectNumber = 1;
      this.currentObjectNumberToCompare = 0;
      this.comparisonsCount = 0;
      this.processIsCompleted = false;

      this.rankObject = function rankObject() {
        this.comparisonsCount++;
        this.rankingResult.splice(this.currentObjectNumberToCompare, 0, this.newObjectNumber);
        this.newObjectNumber += 1;
        this.currentObjectNumberToCompare = 0;

        if (this.checkEndOfRanking(this.newObjectNumber)) {
          this.processIsCompleted = true;
        }
      }

      this.goToNextObjectToCompare = function goToNextObjectToCompare() {
        this.comparisonsCount++;

        if (this.currentObjectNumberToCompare + 1 >= this.rankingResult.length) {
          this.rankingResult.push(this.newObjectNumber);
          this.newObjectNumber += 1;
          this.currentObjectNumberToCompare = 0;
        } else {
          this.currentObjectNumberToCompare += 1;
        }
      }

      this.checkEndOfRanking = function checkEndOfRanking(newObjectNumber) {
        if (newObjectNumber >= this.objectsCount) {
          return true;
        } else {
          return false;
        }
      }

    }]
  });