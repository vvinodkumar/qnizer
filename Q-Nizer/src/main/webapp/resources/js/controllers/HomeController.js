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
    };

    
    $scope.showNewCustomerModel  = function() {
    	$scope.readOnlyMode = false;
        $scope.resetMessages();
        $scope.editMode = false;
        $('#manageCustomerModal').modal();
        $("#manageCustomerModal").doCenter();
    	
    };
    
    $scope.addNewCustomer = function(customer) {
       
      
        $scope.resetMessages();
        $http.post('home/customer/add', customer).success(function() {
        	$scope.readOnlyMode = false;
        	$scope.setSuccess('Details saved successfully for ' + $scope.customer.name);
            $scope.fetchCustomersList();
            $scope.customer.name = '';
            $scope.customer.mobile = '';
            $scope.customer.suggestedWaitTime = '';
            $scope.customer.specialRequest = '';
            $scope.customer.guestCount = '';
            $scope.customer.token = '';
            $scope.customer.status = '';
            $scope.customer.serviceInTime = '5';
            $scope.customer.serviceRefNo = '0';
           
        }).error(function(response) {
            $scope.setError(response);
        });
    };

    $scope.updateCustomer = function(customer) {
        $scope.resetMessages();

        $http.put('home/customer/update', customer).success(function() {
            $scope.fetchCustomersList();
            $scope.customer.name = '';
            $scope.customer.mobile = '';
            $scope.customer.suggestedWaitTime = '';
            $scope.customer.specialRequest = '';
            $scope.customer.guestCount = '';
            $scope.customer.token = '';
            $scope.customer.status = '';
            $scope.customer.serviceInTime = '5';
            $scope.customer.serviceRefNo = '0';
            $scope.setSuccess('Details saved successfully..');
            $scope.editMode = false;
            $scope.readOnlyMode = false;
            $('#manageCustomerModal').hide();
        }).error(function(response) {
            $scope.setError(response);
        });
    };
    
    $scope.notify = function(customer) {
        $scope.resetMessages();

        $http.put('home/customer/notify', customer).success(function() {
            $scope.customer.serviceInTime = '0';
            $scope.customer.serviceRefNo = '0';
            $scope.editMode = false;
            $scope.customer.name = '';
            $scope.customer.mobile = '';
            $scope.customer.suggestedWaitTime = '';
            $scope.customer.specialRequest = '';
            $scope.customer.guestCount = '';
            $scope.customer.token = '';
            $scope.customer.status = '';
            $scope.customer.serviceInTime = '5';
            $scope.customer.serviceRefNo = '0';
            $scope.fetchCustomersList();
            $scope.setNotificationSuccess('Guest Notified Successfully.');
            $("#notifyForm").hide();
        }).error(function(response) {
            $scope.setNotificationError(response);
        });
    };

    $scope.editCustomer = function(customer) {
        $scope.resetMessages();
        $scope.customer = customer;
        if(customer.status =='Notified' || customer.status =='Arrived' || customer.status == 'NoShow' ) {
        	$scope.readOnlyMode = true;
        	$scope.showTable = true;
        } else  {
        	$scope.showTable = false;
        	$scope.readOnlyMode = false;
        }
        $scope.editMode = true;
        $('#manageCustomerModal').modal();
        $("#manageCustomerModal").doCenter();
        
    };
    

    $scope.noShowCustomer = function(customer) {
        $scope.resetMessages();
        $scope.customer = customer;
        if(customer.status =='Notified' || customer.status =='Arrived' || customer.status == 'NoShow' ) {
        	$scope.readOnlyMode = true;
        	$scope.showTable = true;
        } else  {
        	$scope.showTable = false;
        	$scope.readOnlyMode = false;
        }
        $scope.customer.status='NoShow';
        $scope.editMode = true;
        $('#manageCustomerModal').modal();
        $("#manageCustomerModal").doCenter();
        
    };
    

    $scope.arrivedCustomer = function(customer) {
        $scope.resetMessages();
        $scope.customer = customer;
        if(customer.status =='Notified' || customer.status =='Arrived' || customer.status == 'NoShow' ) {
        	$scope.readOnlyMode = true;
        	$scope.showTable = true;
        } else  {
        	$scope.showTable = false;
        	$scope.readOnlyMode = false;
        }
        $scope.customer.status='Arrived';
        $scope.editMode = true;
        $('#manageCustomerModal').modal();
        $("#manageCustomerModal").doCenter();
        
    };

    $scope.notifyCustomer = function(customer) {
    	$("#notifyForm").show();
    	$scope.readOnlyMode = false;
        $scope.resetMessages();
        $scope.customer = customer;
        $scope.editMode = false;
        $('#notificationModal').modal();
        $("#notificationModal").doCenter();
        
    };
    
   

    
    $scope.resetMessages = function() {
        $scope.error = false;
        $scope.errorMessage = '';
        $scope.successMessage = '';
        $scope.notificationSuccessMessage = '';
        $scope.notifcationErrorMessage = '';
        $scope.notificationSuccess = false;
        $scope.notificationError = false;
        $scope.success = false;
      
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
    
    $scope.setNotificationSuccess = function(message) {
    	$scope.resetMessages();
        $scope.notificationSuccess = true;
        $scope.notificationSuccessMessage = message;
    };
    
    $scope.setNotificationError = function(response) {
    	$scope.resetMessages();
        $scope.notificationError = true;
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
        $scope.notifcationErrorMessage = message;
    };

    $scope.fetchCustomersList();
    $scope.predicate = 'token';
};