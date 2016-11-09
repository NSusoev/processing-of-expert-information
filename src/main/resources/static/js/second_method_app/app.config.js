angular.
  module('secondRankingMethodApp').
  config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {
      $locationProvider.hashPrefix('!');

      $routeProvider.
        when('/', {
          template: '<start-form></start-form>'
        }).
        when('/ranking/:objectsCount/', {
          template: '<ranking-form></ranking-form>'
        }).
        otherwise('/', {
          template: '<start-form></start-form>'
        });
    }
  ]);