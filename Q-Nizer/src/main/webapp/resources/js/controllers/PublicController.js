'use strict';

/**
 * PublicController
 * @constructor
 */
var PublicController = function($scope, $http) {

	$scope.showTerms = function() {
	        $('#termsModal').modal();
	        $("#termsModal").doCenter();
	};

	  $scope.resetMessages = function() {
	        $scope.error = false;
	        $scope.errorMessage = '';
	        $scope.successMessage = '';
	        $scope.success = false;
	    };
	  
	 $scope.signup = function(user) {
	        $scope.resetMessages();
	        $http.post('public/business/signup', user).success(function() {
	        	$scope.setSuccess('User Registration Completed');
	        	$location.path("/home");
	        }).error(function(response) {
	            $scope.setError(response);
	        });
	    };
	 
	 $scope.setError = function(response) {
	    	$scope.resetMessages();
	        $scope.error = true;
	        var message = response.rootError.errorDesc + "<BR/><BR/>";
	        message +=   "Txn Ref No: " + response.txnRefNo  + "<BR/>"
	        for (var key in response.errors)
	        {
	           if (result.hasOwnProperty(key))
	           {
	              // here you have access to
	              var errorCode = result[key].errorCode;
	              var errorDesc = result[key].errorDesc;
	              message +=  errorCode + ": " + errorDesc;
	           }
	        }
	        $scope.errorMessage = message;
	    };
	    $scope.setSuccess = function(message) {
	    	$scope.resetMessages();
	    	$scope.success = true;
	        $scope.successMessage = message;
	    };
};