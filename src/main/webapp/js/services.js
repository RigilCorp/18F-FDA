var fdaServices = angular.module('fda.services', []);



/*
 *FDA DATA SERVICE
 * service to communicate to preference backend service. service uses json contract to communicate with backend.
 *
 */

fdaServices.service('FdaDataService', ['$http', '$log', '$rootScope',function($http, $log, $rootScope){

    var service = {};

    //Search for a preference list using category [device, drug, and food]
    function searchPreference(category, callback){
        var request = requestTemplates.createFdaDataRequest();
        request.enterpriseDocument.documentBody.request.requestMethod = 'GET';
        request.enterpriseDocument.documentBody.request.requestCriteria = category;


        $http.post(FDA_FDA_DATA_URL, request)
        .success(function(data, status, headers, config) {
            var response = {};
            response.success = true;
            response.searchData = data.enterpriseDocument.documentBody.response.responseMessage.fdaDataList;
            callback(response);
        })
        .error(function(data, status, headers, config) {
            response = {
                    success : false,
                    message : 'Unknown error'
                };
                callback(response);
        });
    }

    function getPreference(callback){
        var request = requestTemplates.createPreferenceRequest();
        request.enterpriseDocument.documentBody.request.requestMethod = 'GET';
        request.enterpriseDocument.documentBody.request.requestCriteria = 'email';
        var email = $rootScope.globals.currentUser.username;

        request.enterpriseDocument.documentBody.request.requestMessage.userRequest.email = email;

        $http.post(FDA_SAVE_PREFERENCES_URL, request)
        .success(function(data, status, headers, config) {
            var response = {};
            response.success = true;
            response.preferenceObjects = data.enterpriseDocument.documentBody.response.responseMessage.user.preferencesList;
            callback(response);
        })
        .error(function(data, status, headers, config) {
            response = {
                    success : false,
                    message : 'Unknown error'
                };
                callback(response);
        });
    }

    function savePreferences(preferences, callback){

        var request = requestTemplates.createPreferenceRequest();
        request.enterpriseDocument.documentBody.request.requestMethod = 'UPDATE';
        request.enterpriseDocument.documentBody.request.requestCriteria = 'EMAIL';

        var username = $rootScope.globals.currentUser.username;

        request.enterpriseDocument.documentBody.request.requestMessage.userRequest.email = username;

        for(var i = 0; i < preferences.length; i++){
            if(preferences[i].status === 'new'){
                var preferenceObject = requestTemplates.createPreferenceObject();
                preferenceObject.fdaData.dataCode = preferences[i].category;
                if(preferences[i].match > 0 && preferences[i].selectedObject){
                    preferenceObject.fdaData.dataName = preferences[i].selectedObject.title;
                }
                else {
                    preferenceObject.fdaData.dataName = preferences[i].searchedKeyword;
                }
                 request.enterpriseDocument.documentBody.request.requestMessage.userRequest.preferencesList.push(preferenceObject);
            }
        }
         $http.post(FDA_SAVE_PREFERENCES_URL, request)
        .success(function(data, status, headers, config) {
            var response = {};
            response.success = true;
            response.preferenceObjects = data.enterpriseDocument.documentBody.response.responseMessage.user.preferencesList;
            callback(response);
        })
        .error(function(data, status, headers, config) {
            response = {
                    success : false,
                    message : 'Unknown error'
                };
                callback(response);
        });
    }

    function saveSearchKeyword(category, keyword, callback){

        var request = requestTemplates.createFdaDataRequest();
        request.enterpriseDocument.documentBody.request.requestMethod = 'PUT';
        request.enterpriseDocument.documentBody.request.requestCriteria = category;
        request.enterpriseDocument.documentBody.request.requestMessage.fdaDataRequest.dataCode = category;
        request.enterpriseDocument.documentBody.request.requestMessage.fdaDataRequest.dataName = keyword;
        request.enterpriseDocument.documentBody.request.requestMessage.fdaDataRequest.dataDesc = keyword;

        $http.post(FDA_FDA_DATA_URL, request)
        .success(function(data, status, headers, config) {
            var response = {};
            response.success = true;
            callback(response);
        })
        .error(function(data, status, headers, config) {
            response = {
                    success : false,
                    message : 'Unknown error'
                };
                callback(response);
        });
    }

    service.searchPreference = searchPreference;
    service.savePreferences  = savePreferences;
    service.saveSearchKeyword = saveSearchKeyword;
    service.getPreference = getPreference;
    return service;

}]);


/*
 * LOGIN SERVICE
 */
fdaServices.factory('AuthenticationService', [ '$http', '$cookieStore', '$rootScope', '$log',
    function($http, $cookieStore, $rootScope, $log) {

        //Login method
        function login(username, password, callback) {

            var request = requestTemplates.createAuthenticationRequest();
            request.enterpriseDocument.documentBody.request.requestMethod = 'GET';
            request.enterpriseDocument.documentBody.request.requestMessage.authenticationRequest.username = username;
            request.enterpriseDocument.documentBody.request.requestMessage.authenticationRequest.password = password;

            $http.post(FDA_AUTHENTICATION_URL, request)
            .success(function(data, status, headers, config) {
                var result = data.enterpriseDocument.documentBody.response.responseMessage.authenticationResponse.result;
                var response = {}
                if(result === 'SUCCESS'){
                     response.success = true;
                }
                else {
                    response.success = false,
                    response.message = "Invalid username or password"
                }
                callback(response);
            })
            .error(function(data, status, headers, config) {
                response = {
                    success : false,
                     message : 'Unable to connect to server'
                };
                callback(response);
            });
        }

         function setCredentials(username, password) {
            var authdata = Base64.encode(username + ':' + password);
            $rootScope.globals = {
                currentUser: {
                    username: username,
                    authdata: authdata
                }
            };

            $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line
            $cookieStore.put('globals', $rootScope.globals);
        }


        function clearCredentials() {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common.Authorization = 'Basic ';
        }

         // Base64 encoding service used by AuthenticationService
        var Base64 = {

            keyStr: 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=',

            encode: function (input) {
                var output = "";
                var chr1, chr2, chr3 = "";
                var enc1, enc2, enc3, enc4 = "";
                var i = 0;

                do {
                    chr1 = input.charCodeAt(i++);
                    chr2 = input.charCodeAt(i++);
                    chr3 = input.charCodeAt(i++);

                    enc1 = chr1 >> 2;
                    enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                    enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                    enc4 = chr3 & 63;

                    if (isNaN(chr2)) {
                        enc3 = enc4 = 64;
                    } else if (isNaN(chr3)) {
                        enc4 = 64;
                    }

                    output = output +
                        this.keyStr.charAt(enc1) +
                        this.keyStr.charAt(enc2) +
                        this.keyStr.charAt(enc3) +
                        this.keyStr.charAt(enc4);
                    chr1 = chr2 = chr3 = "";
                    enc1 = enc2 = enc3 = enc4 = "";
                } while (i < input.length);

                return output;
            },

            decode: function (input) {
                var output = "";
                var chr1, chr2, chr3 = "";
                var enc1, enc2, enc3, enc4 = "";
                var i = 0;

                // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
                var base64test = /[^A-Za-z0-9\+\/\=]/g;
                if (base64test.exec(input)) {
                    window.alert("There were invalid base64 characters in the input text.\n" +
                        "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                        "Expect errors in decoding.");
                }
                input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

                do {
                    enc1 = this.keyStr.indexOf(input.charAt(i++));
                    enc2 = this.keyStr.indexOf(input.charAt(i++));
                    enc3 = this.keyStr.indexOf(input.charAt(i++));
                    enc4 = this.keyStr.indexOf(input.charAt(i++));

                    chr1 = (enc1 << 2) | (enc2 >> 4);
                    chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                    chr3 = ((enc3 & 3) << 6) | enc4;

                    output = output + String.fromCharCode(chr1);

                    if (enc3 != 64) {
                        output = output + String.fromCharCode(chr2);
                    }
                    if (enc4 != 64) {
                        output = output + String.fromCharCode(chr3);
                    }

                    chr1 = chr2 = chr3 = "";
                    enc1 = enc2 = enc3 = enc4 = "";

                } while (i < input.length);

                return output;
            }
        };


        var service = {};
        service.login = login;
        service.clearCredentials = clearCredentials;
        service.setCredentials = setCredentials
        return service;

    }]);


//REGISTRATION SERVICE to register new user.
fdaServices.factory('RegistrationService', ['$http','$log', function($http, $log){

    var service = {};

    //Register method
    function register(userRegistrationData, callback){

        var request = requestTemplates.createRegistrationRequest()
        request.enterpriseDocument.documentBody.request.requestMethod = 'PUT';
        request.enterpriseDocument.documentBody.request.requestMessage.userRequest.firstName = userRegistrationData.firstname;
        request.enterpriseDocument.documentBody.request.requestMessage.userRequest.middleName = userRegistrationData.middlename;
        request.enterpriseDocument.documentBody.request.requestMessage.userRequest.lastName = userRegistrationData.lastname;
        request.enterpriseDocument.documentBody.request.requestMessage.userRequest.email = userRegistrationData.email;
        request.enterpriseDocument.documentBody.request.requestMessage.userRequest.phone = userRegistrationData.phone;
        request.enterpriseDocument.documentBody.request.requestMessage.userRequest.zipcode = userRegistrationData.zipcode;

        $http.post(FDA_REGISTRATION_URL, request)
        .success(function(data, status, headers, config) {
            var response = {
                success : true
            };
            callback(response);
        })
        .error(function(data, status, headers, config) {
            response = {
                success : false,
                 message : 'Unable to save data.'
            };
            callback(response);
        });
    }

    service.register = register;
    return service;
}]);