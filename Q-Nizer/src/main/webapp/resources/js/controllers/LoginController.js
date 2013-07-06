'use strict';

function LoginController($scope, $http, authService) {
		
	$scope.login = function(){
		var data = "j_username="+$scope.username+"&j_password="+$scope.password+"&submit=Login";
		$http.post('j_spring_security_check', data, {
			  headers: {
			    'Content-Type': 'application/x-www-form-urlencoded',
			  }
		}).
	    success(function(data, status, headers, config) {
	    	$scope.error = false;
	    	 authService.loginConfirmed();
	    }).
	    error(function(data, status, headers, config){
	    	 authService.loginFailed();
	    });
	};
	
	$scope.launchLogin= function(){
		$scope.error = false;
		login.modal();
	};
	
	$scope.logout = function(){
		$http.get('j_spring_security_logout')
		.success(function(data, status, headers, config){
			authService.loggedOut();
		});
	};
}