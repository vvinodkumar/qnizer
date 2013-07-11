'use strict';

var AngularSpringApp = {};

var App = angular.module('AngularSpringApp', ['AngularSpringApp.filters', 'AngularSpringApp.services', 'AngularSpringApp.directives','http-auth-interceptor','angular-auth']);

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

    $routeProvider.when('/dashboard', {
        templateUrl: 'dashboard/layout',
        controller: HomeController
    });
  
    $routeProvider.when('/public/signup', {
        templateUrl: 'public/signup',
        controller: PublicController
    });
    
    $routeProvider.otherwise({redirectTo: '/public/signup'});
   
}]);



