angular.module('angular-auth', ['http-auth-interceptor'])
  .directive('qnizer', function() {
    return {
      restrict: 'C',
      link: function(scope, elem, attrs) {
        //once Angular is started, remove class:
        elem.removeClass('waiting-for-angular');
        
        var login = elem.find('#login-holder');
        var main = elem.find('#main-content');
        var menu = elem.find('#menu');
        login.modal("hide");
        
        
        scope.error = false;
        scope.$on('event:auth-loginRequired', function() {
        	menu.hide();	
          login.modal();
          main.hide();
          
        });
        scope.$on('event:auth-loginConfirmed', function() {
          main.show();
          menu.show();
          login.modal("hide");
        });
        scope.$on('event:auth-loginFailed', function() {
        	menu.hide();
            scope.error = true;
            main.hide();
            
          });
        scope.$on('event:auth-loggedOut', function() {
        	menu.hide();
            login.modal();
            main.hide();
            
          });

      }
    }
  });