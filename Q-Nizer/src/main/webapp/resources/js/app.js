'use strict';

var AngularSpringApp = {};

var App = angular.module('AngularSpringApp', ['AngularSpringApp.filters', 'AngularSpringApp.services', 'AngularSpringApp.directives']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/home', {
        templateUrl: 'home/layout',
        controller: HomeController
    });

    $routeProvider.when('/home/customer/list', {
        templateUrl: 'home/layout',
        controller: HomeController
    });


    $routeProvider.otherwise({redirectTo: '/home'});
}]);

App.config(function($httpProvider) {
	  function responseHandlerInterceptor($q,$log) {
	    function success(response) {
	    	return response;
	    }
	    function error(response) {
	      return $q.reject(response); //similar to throw response;
	    }
	    return function(promise) {
	      return promise.then(success, error);
	    }
	  }
	  $httpProvider.responseInterceptors.push(responseHandlerInterceptor);
});