var controllers = angular.module('fda.controllers',[]);

//LOGIN CONTROLLER
controllers.controller('loginController', ['$rootScope', '$scope', '$log', '$location', 'AuthenticationService',
    function($rootScope, $scope, $log, $location, AuthenticationService){

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

        //Sign in user
         $scope.signin = function(isValid){

             if(!isValid){
                 return;
             }
             $scope.dataloading = true;
             AuthenticationService.login($scope.data.username, $scope.data.password, function (response) {
                 if (response.success) {
                    AuthenticationService.setCredentials($scope.data.username, $scope.data.password);
                    var redirectTo = null
                    if($rootScope.redirectTo){
                    	redirectTo = $rootScope.redirectTo;	
                    }
                    else {
                    	redirectTo = '/preference';
                    }
                    
                    $rootScope.redirectTo = null;
                    $location.path(redirectTo);

                } else {
                     $scope.dataloading = false;
                     $scope.data.password = '';
                     $scope.loginForm.password.$setUntouched();
                     $scope.error = response.message;
                }
            });
         }
         
         //Reset Valiation to clear error message
         $scope.resetTouched = function(formField){
         	formField.$setUntouched();
         }
}]);

//LOGOUT CONTROLLER
controllers.controller('logoutController', ['$scope', '$log', '$location', 'AuthenticationService', function($scope, $log, $location, AuthenticationService){

    (function initController(){
        AuthenticationService.clearCredentials();
        $location.path('/login');
    })();
}]);

//REGISTRATION CONTROLLER.
controllers.controller('registrationController', ['$scope', '$log', '$location', '$modal', 'RegistrationService',
    function($scope, $log, $location, $modal, RegistrationService){

		//save user registration data.
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
                    
                    var modelResult = $modal.open({
                        animation: true,
                        templateUrl: 'pages/registration-modal.html',
                        controller: 'registrationModalController',
                        size: 'modal-sm',
                        resolve : {
                            username : function(){
                                return registrationData.email;
                            }
                        }
                    });
                    
                    modelResult.result.then(function(){
                        $location.path("/login");
                    }, function(){
                        
                    });
                }
                else {
                    $scope.dataloading = false;
                    $scope.error = response.message;
                }
            });
        }
        
        //Reset Valiation to clear error message
        $scope.resetTouched = function(formField){
        	formField.$setUntouched();
        }
}]);

//REGISTRATION MODAL WINDOW CONTROLLER
controllers.controller('registrationModalController',['$scope', '$log', '$modalInstance', 'username', function($scope, $log, $modalInstance, username){
    $scope.username = username;
    
    $scope.ok = function(){
        $modalInstance.close({});
    }
}]);


controllers.controller('preferenceController', ['$scope', '$log', '$filter', '$timeout', '$routeParams', 'FdaDataService', function($scope, $log, $filter, $timeout, $routeParams, FdaDataService){

    //Holds all preferences choice.
    $scope.preferences = [];
    $scope.searchPlaceholder = {Device: "Search for a device", Drug: "Search for a Drug", Food: "Search for a Food"};
    $scope.adverseReportData = null;
    $scope.enforcementReportData = null;

    //function runs at controller load.
    (function initController(){
        FdaDataService.getPreference(function(response){
            if(response.success){
                handleResponse(response);
                if($routeParams.device && $routeParams.name){
                    var category = $filter('capitalize')($routeParams.device);
                    var preferences = $filter('filter')($scope.preferences, {category:category});
                    var preference = $filter('filter')(preferences, {searchStr:$routeParams.name});
                    if(preference.length > 0){
                        $scope.adverseReportData =  $filter('orderBy')(preference[0].eventResultsList, ['-dateOfEvent']); // preference[0].eventResultsList;
                        $scope.enforcementReportData = $filter('orderBy')(preference[0].enforcementResultsList, ['-recallInitiationDate']); //preference[0].enforcementResultsList;
                    }
                }
            }
        });
    })();


    function handleResponse(response){
    	for(var i = 0; i < response.preferenceObjects.length; i++){
            var id = i+1;
            var category = $filter('capitalize')(response.preferenceObjects[i].fdaJsonDataObject.dataCode);
            var searchStr = response.preferenceObjects[i].fdaJsonDataObject.dataName;
            var eventResultsList = response.preferenceObjects[i].fdaResponse.eventResultsList;
            var enforcementResultsList = response.preferenceObjects[i].fdaResponse.enforcementResultsList;
            var preference = {id : id,
                    searchData : [],
                    searchedKeyword:'',
                    showAddButton:false,
                    category : category,
                    isSearchKeywordAdded:false,
                    addKeyword:'',
                    searchStr: searchStr,
                    status : 'saved',
                    eventResultsList: eventResultsList,
                    enforcementResultsList: enforcementResultsList
                };
            $scope.preferences.push(preference);
        }
    }
    

    //Add prefernece object dynamicly to show preferences in UI.
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

    //trigger to populate correct search results base on preference selection.
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


    //save search keyword
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

    //save preferences
    $scope.savePreferences = function(){
        $scope.dataloading = true;
        FdaDataService.savePreferences($scope.preferences, function(response){
            if(response.success){
                $scope.preferences.splice(0, $scope.preferences.length);
            	handleResponse(response);
            	
            	
            	/*for(var i = 0; i < $scope.preferences.length; i ++){
                    $scope.preferences[i].status = 'saved';
                    var eventResultsList = response.preferenceObjects[i].fdaResponse.eventResultsList;
                    var enforcementResultsList = response.preferenceObjects[i].fdaResponse.enforcementResultsList;
                    $scope.preferences[i].eventResultsList = eventResultsList;
                    $scope.preferences[i].enforcementResultsList =  enforcementResultsList;

                }*/
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

    //populate report data to result.
    $scope.viewReport = function(index){
        $scope.adverseReportData =  $filter('orderBy')($scope.preferences[index].eventResultsList, ['-dateOfEvent']); //$scope.preferences[index].eventResultsList;
        $scope.enforcementReportData = $filter('orderBy')($scope.preferences[index].enforcementResultsList, ['-recallInitiationDate']); //$scope.preferences[index].enforcementResultsList;
    }
}]);

//MAIN CONTROLLER
controllers.controller('mainController', ['$scope', '$log', '$location', function($scope, $log, $location){

    $scope.showLogin = function(){
        return !($.inArray($location.path(), ['/registration']) === -1);
    }

    $scope.showLogout = function(){
        return !($location.path().indexOf('/preference') === -1);
        //return !($.inArray($location.path(), ['/preference']) === -1);
    }
}])


