angular.module('angular-auth', ['http-auth-interceptor'])
  .directive('qnizer', function() {
    return {
      restrict: 'C',
      link: function(scope, elem, attrs) {
        //once Angular is started, remove class:
        elem.removeClass('waiting-for-angular');
        
        var login = elem.find('#login-holder');
        var main = elem.find('#main-content');
        login.modal("hide");
        
        
        scope.error = false;
        scope.$on('event:auth-loginRequired', function() {
          login.modal();
          main.hide();
          
        });
        scope.$on('event:auth-loginConfirmed', function() {
          main.show();
          login.modal("hide");
        });
        scope.$on('event:auth-loginFailed', function() {
            scope.error = true;
            main.hide();
            
          });
        scope.$on('event:auth-loggedOut', function() {
            login.modal();
            main.hide();
            
          });

      }
    }
  });