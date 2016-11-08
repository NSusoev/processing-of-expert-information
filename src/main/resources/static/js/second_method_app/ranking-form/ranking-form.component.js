angular.
  module('secondRankingMethodApp').
  component('rankingForm', {
    templateUrl: '/js/second_method_app/ranking-form/ranking-form.template.html',
    controller: ['$routeParams', function RankingFormController($routeParams) {
      this.objectsCount = $routeParams.objectsCount;
      this.newObjectNumber = $routeParams.newObjectNumber;
      this.newObjectInsertIndex = $routeParams.newObjectInsertIndex;


    }]
  });