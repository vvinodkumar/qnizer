'use strict';

/**
 * HomeController
 * @constructor
 */
var HomeController = function($scope, $http) {
    $scope.fetchCustomersList = function() {
        $http.get('home/customer/list').success(function(response){
            $scope.customers = response.responseCollection;
        });
    }

    $scope.addNewCustomer = function(customer) {
       
      
        $scope.resetError();
        $http.post('home/customer/add', customer).success(function() {
            $scope.fetchCustomersList();
            $scope.customer.name = '';
            $scope.customer.mobile = '';
            $scope.customer.suggestedWaitTime = '';
            $scope.customer.specialRequest = '';
            $scope.customer.guestCount = '';
            $scope.customer.token = '';
            $scope.setSuccess('Details saved successfully.');
        }).error(function(response) {
            $scope.setError(response);
        });
    }

    $scope.updateCustomer = function(customer) {
        $scope.resetError();

        $http.put('home/customer/update', customer).success(function() {
            $scope.fetchCustomersList();
            $scope.customer.name = '';
            $scope.customer.mobile = '';
            $scope.customer.suggestedWaitTime = '';
            $scope.customer.specialRequest = '';
            $scope.customer.guestCount = '';
            $scope.customer.token = '';
            $scope.setSuccess('Details saved successfully..');
            $scope.editMode = false;
        }).error(function(response) {
            $scope.setError(response);
        });
    }

    $scope.editCustomer = function(customer) {
        $scope.resetError();
        $scope.customer = customer;
        $scope.editMode = true;
    }

    $scope.removeCustomer = function(customerToken) {
    	$scope.resetError();
        $http.delete('home/customer/delete' + customerToken).success(function() {
            $scope.fetchCustomersList();
        });
    }

    
    $scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    }


    $scope.setError = function(response) {
        $scope.error = true;
        $scope.success = false;
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
    }
    $scope.setSuccess = function(message) {
        $scope.success = true;
        $scope.error = false;
        $scope.successMessage = message;
    }

    $scope.fetchCustomersList();
    $scope.predicate = 'token';
}