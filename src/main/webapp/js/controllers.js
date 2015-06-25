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


controllers.controller('preferenceController', ['$scope', '$log', '$filter', '$timeout', 'FdaDataService', function($scope, $log, $filter, $timeout, FdaDataService){
    
    //Holds all preferences choice. 
	$scope.preferences = [];
    $scope.searchPlaceholder = {Device : "Search for a device"};
    
    (function initController(){
    	FdaDataService.getPreference(function(response){
    		if(response.success){
    			for(var i = 0; i < response.preferenceObjects.length; i++){
    				var id = i+1;
    				var category = $filter('capitalize')(response.preferenceObjects[i].fdaData.dataCode);
    				var searchStr = response.preferenceObjects[i].fdaData.dataName;
    				var preference = {id : id, 
    						searchData : [],
    						searchedKeyword:'',
    						showAddButton:false,
    						category : category,
    						isSearchKeywordAdded:false, 
    		        		addKeyword:'',
            				searchStr: searchStr,
                    		status : 'saved'};
    				$scope.preferences.push(preference);
    			}
    		}
    		
    	});
    })();
    
    
    
    
    $scope.addPreference = function(){
        var newItemNumber = $scope.preferences.length + 1;
        var preference = {id : newItemNumber, 
        		searchData : [], 
        		searchedKeyword:'', 
        		showAddButton:false, 
        		isSearchKeywordAdded:false, 
        		addKeyword:'',
        		searchStr: '',
        		status : 'new'};
       
        $scope.$watch(function(){
        	return preference.searchedKeyword;
        }, function(value){
       
        	preference.isSearchKeywordAdded = false
        	 if(preference.match <= 0){
        		
        			 preference.showAddButton = true;
        		    
        	 }
        	 else {
        			 preference.showAddButton = false;
        		
        	 }
        });
        
        $scope.preferences.push(preference);
    }
    
    $scope.showSetPreferenceBtn = function(preference){
        return $scope.preferences.length === preference.id;
    }
    
    $scope.preferenceSelectChanged = function(index){
        var searchData = FdaDataService.searchPreference($scope.preferences[index].category, function(response){
             if(response.success){
            	 $scope.preferences[index].searchData.splice(0, $scope.preferences[index].searchData.length);
            	 for(var i = 0; i < response.searchData.length; i++){
            		 $scope.preferences[index].searchData.push(response.searchData[i]);
            	 }
             }
        });
    }
    
   
    
    $scope.addSearchKeyword = function(index){
    	$scope.preferences[index].isSearchKeywordAdded = true;
    	$scope.preferences[index].addKeyword = $scope.preferences[index].searchedKeyword;
    	FdaDataService.saveSearchKeyword(
    			$scope.preferences[index].category,
    			$scope.preferences[index].searchedKeyword,
    			function(response){
    				if(response)
    					$log.info('Searchkeyword stored, ', $scope.preferences[index].searchedKeyword);
    			});
    }
    
    $scope.savePreferences = function(){
    	$scope.dataloading = true;
    	FdaDataService.savePreferences($scope.preferences, function(response){
    		if(response.success){
    			for(var i = 0; i < $scope.preferences.length; i ++){
    				$scope.preferences[i].status = 'saved';
    			}
    			$scope.dataloading = false;
    			$scope.dataSaved = true;
    			$timeout(function(){
    				$scope.dataSaved = false;
    			}, 2000);
    		}
    		else {
    			$log.log('savePreference Error');
    		}
    		
    	});
    }
}]);

controllers.controller('mainController', ['$scope', '$log', '$location', function($scope, $log, $location){

	$scope.showLogin = function(){
        return !($.inArray($location.path(), ['/registration']) === -1); 
    }
    
    $scope.showLogout = function(){
        return !($.inArray($location.path(), ['/preference']) === -1);
    }
}])


