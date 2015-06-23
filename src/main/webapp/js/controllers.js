var controllers = angular.module('fda.controllers',[]);


controllers.controller('loginController', ['$scope', '$log', '$location', 'AuthenticationService', 
    function($scope, $log, $location, AuthenticationService){
        //Initialize Controller
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
                     $scope.error = response.message;
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

controllers.controller('registrationController', ['$scope', '$log', '$location', 'RegistrationService',
    function($scope, $log, $location, RegistrationService){
    
        
        $scope.submitRegistrationForm = function(isValid){
            if(!isValid){
                return;
            }
            $scope.dataloading = true; 
            var registrationData = {};
            registrationData.firstname = $scope.data.firstname;
            registrationData.middlename = $scope.data.middlename;
            registrationData.lastname = $scope.data.lastname;
            registrationData.email = $scope.data.email;
            registrationData.phone = $scope.data.phone;
            registrationData.zipcode = $scope.data.zipcode
            
            RegistrationService.register(registrationData, function(response){
                if(response.success){
                    $location.path("/login");
                }
                else {
                    $scope.dataloading = false;
                    $scope.error = response.message;
                }
            });
        }
}]);


controllers.controller('preferenceController', ['$scope', '$log', function($scope, $log){
    
    $scope.preferences = [];
    
    $scope.addPreference = function(){
        var newItemNumber = $scope.preferences.length + 1;
        $scope.preferences.push({id : newItemNumber});
    }
    
    $scope.showSetPreferenceBtn = function(preference){
        return $scope.preferences.length === preference.id;
    }
    
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


