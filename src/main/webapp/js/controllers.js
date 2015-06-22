var controllers = angular.module('fda.controllers',[]);


controllers.controller('loginController', ['$scope', '$log', '$location', 'AuthenticationService', 
    function($scope, $log, $location, AuthenticationService){
        $log.info('LoginController executing....');
        
        (function initController(){
            // reset login status
            AuthenticationService.clearCredentials();    
        })();
        
        var data = {};
        $scope.data = {};
        $scope.data.username = '';
        $scope.data.password = '';
        $scope.error = '';
        
         $scope.signin = function(isValid){
             $log.info('isValid: ', isValid);
             if(!isValid){
                 $log.info('Login in form is not valid');
                 return;
             }
             $scope.dataloading = true;   
             AuthenticationService.login($scope.data.username, $scope.data.password, function (response) {
                 if (response.success) {
                    AuthenticationService.setCredentials($scope.data.username, $scope.data.password);
                    $location.path('/preference');
                } else {
                     $scope.dataloading = false;
                     $scope.error = 'Invalid username or password';
                }
            });
         }
}]);

controllers.controller('logoutController', ['$scope', '$log', '$location', 'AuthenticationService', function($scope, $log, $location, AuthenticationService){
    $log.info('logoutController Executing');
    (function initController(){
        AuthenticationService.clearCredentials();
        $location.path('/login');
    })();
}]);

controllers.controller('registrationController', ['$scope', '$log', function($scope, $log){
     $log.info('RegisterController executing....');
    
}]);


controllers.controller('preferenceController', ['$scope', '$log', function($scope, $log){
     $log.info('PreferenceController executing....');
    
}]);

controllers.controller('mainController', ['$scope', '$log', '$location', function($scope, $log, $location){
    $log.info('MainController executing....');
    $scope.showLogin = function(){
        return !($.inArray($location.path(), ['/registration']) === -1); 
    }
    
    $scope.showLogout = function(){
        return !($.inArray($location.path(), ['/preference']) === -1);
    }
}])


