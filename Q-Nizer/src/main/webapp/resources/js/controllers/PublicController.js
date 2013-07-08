var PublicController = function($scope, $http) {

	$scope.showTerms = function() {
	        $('#termsModal').modal();
	}

	 $scope.signup = function(user) {
	        $scope.resetMessages();
	        $http.post('public/business/signup', user).success(function() {
	        	$scope.setSuccess('Details saved successfully for ' + $scope.customer.name);
	        }).error(function(response) {
	            $scope.setError(response);
	        });
	    }
}