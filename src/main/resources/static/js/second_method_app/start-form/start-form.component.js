angular.
  module('secondRankingMethodApp').
  component('startForm', {
    templateUrl: '/js/second_method_app/start-form/start-form.template.html',
    controller: function StartFormController() {
      this.objectsCount = null;
      this.hasErrors = false;

      this.validateForm = function validateForm(objectsCount, $event) {
        if (!objectsCount.match(/^[1-9][0-9]*$/)) {
          $event.preventDefault();
          this.hasErrors = true;
        } 
      }

    }
  });