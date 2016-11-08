angular.
  module('secondRankingMethodApp').
  component('startForm', {
    templateUrl: '/js/second_method_app/start-form/start-form.template.html',
    controller: function StartFormController() {
      this.objectsCount = null;

      this.startRanking = function startRanking(objectsCount) {
        console.log("objects count = " + objectsCount);
      }
    }
  });